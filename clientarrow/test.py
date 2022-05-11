
import pyarrow as pa
import pyarrow.flight
from packaging import version
import pandas as pd


df =pd.read_csv("green.csv")

clientArrowFlight = pa.flight.connect("grpc://0.0.0.0:8815")


data_table = pa.Table.from_pandas(df)

upload_descriptor = pa.flight.FlightDescriptor.for_path("uploaded.parquet")
writer, _ = clientArrowFlight.do_put(upload_descriptor, data_table.schema)
writer.write_table(data_table)
writer.close()



flight = clientArrowFlight.get_flight_info(upload_descriptor)
descriptor = flight.descriptor
reader = clientArrowFlight.do_get(flight.endpoints[0].ticket)
read_table = reader.read_all()
data_pandas = read_table.to_pandas()

for j in data_pandas[data_pandas.columns].to_dict(orient="records"):
	print(j)