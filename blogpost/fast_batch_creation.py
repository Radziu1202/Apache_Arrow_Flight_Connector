import random
import time
import uuid

from pyarrow import RecordBatch, schema, utf8, float64, int64

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
        batch = []

        start = time.time()
        i = 0
        for data in data_collection:
            batch.append({key: value for (key, value) in data.items()})
            i += 1
            if i % 10_000 == 0:
                RecordBatch.from_pylist(batch, schema=schema_root)
                batch = []
        end = time.time()

        print(end - start)
