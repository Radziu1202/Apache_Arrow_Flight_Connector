# The way of creating batch can make a difference

## Introduction
During working with data in most cases we focus on minimizing time spend on I/O operation.
One of the most common ways to reduce the number of write operation is using batches.
We want to gather some data chunk and then send them as one operation.
However, we should never forget about trying to optimize also a process of creating the batch.
In this post, we would like to show a small tip which enable us to avoid problem working with PyArrow

## PyArrow
PyArrow is an implementation of Apache Arrow library for Python.
It enables, for example, sending data to Arrow Flight Server.
However, before sending data, they should be prepared by creating `RecordBatch` object.
`RecordBatch` can be created from many types of objects, for example, Pandas Dataframes or list of dictionaries.
Nevertheless, we do not have always data as Dataframes or list of dictionaries.
For this reason, we need prepare our data firstly and doing it in optimal way can have a great impact on performance.

## Preparing data
First, let's generate some test data. 
The data will be initially stored as Python dictionary
```python
data_collection = []

for i in range(100_000):
    data_collection.append({
        'id': i,
        'uuid': str(uuid.uuid4()),
        'value': random.random() * 10_000 + 1
    })
```

The next step is preparing Arrow schema to enable PyArrow correct work
```python
schema_root = schema([
        ('id', int64()),
        ('uuid', utf8()),
        ('value', float64())
    ])
```

## Tests
Let's assume that we want to send our data in batches of size equals to 10 000 objects.
Firstly we will use Pandas Dataframe.
Then we will use list of dictionaries and compare results

### Using Dataframe from Pandas
```python
batch = pd.DataFrame({'id': pd.Series(dtype='int'),
                      'uuid': pd.Series(dtype='string'),
                      'value': pd.Series(dtype='float')})

start = time.time()
i = 0
for data in data_collection:
    batch = batch.append({key: value for (key, value) in data.items()}, ignore_index=True)
    i += 1
    if i % 10_000 == 0:
        RecordBatch.from_pandas(batch, preserve_index=False, schema=schema_root)
        batch = batch[0:0]
    end = time.time()
```

The average time of executing this code 10 times is equals to 157,4s

### Using list of dictionaries
```python
start = time.time()
i = 0
for data in data_collection:
    batch.append({key: value for (key, value) in data.items()})
    i += 1
    if i % 10_000 == 0:
        RecordBatch.from_pylist(batch, schema=schema_root)
        batch = []
end = time.time()
```

The average time of executing this code 10 times is equals to 0.133s 


## Conclusions
The result obtained using only list of dictionaries is much better than using Dataframes.
Adding new data to dataframe using `append` method is really time-consuming and in consequence creating batches is much more expensive.
Probably this is the reason why this method is deprecated from Pandas 1.4.0 and will be removed in the future.

