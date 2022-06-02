import warnings
import random
import time
import uuid
import pandas as pd

from pyarrow import RecordBatch, schema, utf8, float64, int64

warnings.simplefilter(action='ignore', category=FutureWarning)


if __name__ == '__main__':
    data_collection = []

    for i in range(100_000):
        data_collection.append({
            'id': i,
            'uuid': str(uuid.uuid4()),
            'value': random.random() * 10_000 + 1
        })
    schema_root = schema([
        ('id', int64()),
        ('uuid', utf8()),
        ('value', float64())
    ])

    for j in range(10):
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

        print(end - start)
