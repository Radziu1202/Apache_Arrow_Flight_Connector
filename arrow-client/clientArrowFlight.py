import pyarrow as pa
import pyarrow.flight
from packaging import version
import pandas as pd

df =pd.read_csv("green.csv")

client = pa.flight.connect("grpc://0.0.0.0:8815")


data_table = pa.Table.from_pandas(df)

upload_descriptor = pa.flight.FlightDescriptor.for_path("uploaded.parquet")
writer, _ = client.do_put(upload_descriptor, data_table.schema)
writer.write_table(data_table)
writer.close()

flight = client.get_flight_info(upload_descriptor)
descriptor = flight.descriptor
print("Path:", descriptor.path[0].decode('utf-8'), "Rows:", flight.total_records, "Size:", flight.total_bytes)
print("=== Schema ===")
print(flight.schema)
print("==============")

