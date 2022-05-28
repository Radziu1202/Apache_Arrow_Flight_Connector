import pathlib
import socket
import pyarrow as pa
import pyarrow.flight
import pyarrow.parquet
import random as r


class FlightServer(pa.flight.FlightServerBase):

	def __init__(self, location="grpc://0.0.0.0:8815",
				repo=pathlib.Path("./datasets"), **kwargs):
		super(FlightServer, self).__init__(location, **kwargs)
		self._location = location
		self._repo = repo

		self.dataset= pa.array(self.generate_data())
		
		
	def generate_data(self):
		num=100
		dataset=[]
		for i in range(num):
			dataset.append(self.generate_data_entity(i))
		return dataset
		
	
	
	def generate_data_entity(self,id):
		names=["Noah", "Emma", "Liam", "Olivia", "Jacob", "Sophia", "William", "Isabella", "Mason", "Ava", "Ethan", "Mia",
		"Michael", "Abigail", "Alexander", "Emily", "James", "Charlotte", "Elijah", "Madison", "Benjamin", "Elizabeth",
		"Daniel", "Amelia", "Aiden", "Evelyn", "Logan", "Ella", "Jayden", "Chloe", "Matthew", "Harper", "Lucas",
		"Avery", "David", "Sofia", "Jackson", "Grace", "Joseph", "Addison", "Anthony", "Victoria", "Samuel", "Lily",
		"Joshua", "Natalie", "Gabriel", "Aubrey", "Andrew", "Lillian", "John", "Zoey", "Christopher", "Hannah",
		"Oliver", "Layla", "Dylan", "Brooklyn", "Carter", "Scarlett", "Isaac", "Zoe", "Luke", "Camila", "Henry",
		"Samantha", "Owen", "Riley", "Ryan", "Leah", "Nathan", "Aria", "Wyatt", "Savannah", "Caleb", "Audrey",
		"Sebastian", "Anna", "Jack", "Allison", "Christian", "Gabriella", "Jonathan", "Claire", "Julian", "Hailey",
		"Landon", "Penelope", "Levi", "Aaliyah", "Isaiah", "Sarah", "Hunter", "Nevaeh", "Aaron", "Kaylee", "Charles",
		"Stella", "Thomas", "Mila", "Eli", "Nora", "Jaxon", "Ellie", "Connor", "Bella", "Nicholas", "Lucy", "Jeremiah"
		, "Alexa", "Grayson", "Arianna", "Cameron", "Violet", "Brayden", "Ariana", "Adrian", "Genesis", "Evan",
		"Alexis", "Jordan", "Eleanor", "Josiah", "Maya", "Angel", "Caroline", "Robert", "Peyton", "Gavin", "Skylar",
		"Tyler", "Madelyn", "Austin", "Serenity", "Colton", "Kennedy", "Jose", "Taylor", "Dominic", "Alyssa",
		"Brandon", "Autumn", "Ian", "Paisley", "Lincoln", "Ashley", "Hudson", "Brianna", "Kevin", "Sadie", "Zachary",
		"Naomi", "Adam", "Kylie", "Mateo", "Julia", "Jason", "Sophie", "Chase", "Mackenzie", "Nolan", "Eva", "Ayden",
		"Gianna", "Cooper", "Luna", "Parker", "Katherine", "Xavier", "Hazel", "Asher", "Khloe", "Carson", "Ruby",
		"Jace", "Melanie", "Easton", "Piper", "Justin", "Lydia", "Leo", "Aubree", "Bentley", "Madeline", "Jaxson",
		"Aurora", "Nathaniel", "Faith", "Blake", "Alexandra", "Elias", "Alice", "Theodore", "Kayla", "Kayden",
		"Jasmine", "Luis", "Maria", "Tristan", "Annabelle", "Bryson", "Lauren", "Ezra", "Reagan", "Juan", "Elena",
		"Brody", "Rylee", "Vincent", "Isabelle", "Micah", "Bailey", "Miles", "Eliana", "Santiago", "Sydney", "Cole",
		"Makayla", "Ryder", "Cora", "Carlos", "Morgan", "Damian", "Natalia", "Leonardo", "Kimberly", "Roman", "Vivian",
		"Max", "Quinn", "Sawyer", "Valentina", "Jesus", "Andrea", "Diego", "Willow", "Greyson", "Clara", "Alex",
		"London", "Maxwell", "Jade", "Axel", "Liliana", "Eric", "Jocelyn", "Wesley", "Trinity", "Declan", "Kinsley",
		"Giovanni", "Brielle", "Ezekiel", "Mary", "Braxton", "Molly", "Ashton", "Hadley", "Ivan", "Delilah", "Hayden",
		"Emilia", "Camden", "Josephine", "Silas", "Brooke", "Bryce", "Ivy", "Weston", "Lilly", "Harrison", "Adeline",
		"Jameson", "Payton", "George", "Lyla", "Antonio", "Isla", "Timothy", "Jordyn", "Kaiden", "Paige", "Jonah",
		"Isabel", "Everett", "Mariah", "Miguel", "Mya", "Steven", "Nicole", "Richard", "Valeria", "Emmett", "Destiny",
		"Victor", "Rachel", "Kaleb", "Ximena", "Kai", "Emery", "Maverick", "Everly", "Joel", "Sara", "Bryan",
		"Angelina", "Maddox", "Adalynn", "Kingston", "Kendall", "Aidan", "Reese", "Patrick", "Aliyah", "Edward",
		"Margaret", "Emmanuel", "Juliana", "Jude", "Melody", "Alejandro", "Amy", "Preston", "Eden", "Luca", "Mckenzie",
		"Bennett", "Laila", "Jesse", "Vanessa", "Colin", "Ariel", "Jaden", "Gracie", "Malachi", "Valerie", "Kaden",
		"Adalyn", "Jayce", "Brooklynn", "Alan", "Gabrielle", "Kyle", "Kaitlyn", "Marcus", "Athena", "Brian", "Elise",
		"Ryker", "Jessica", "Grant", "Adriana", "Jeremy", "Leilani", "Abel", "Ryleigh", "Riley", "Daisy", "Calvin",
		"Nova", "Brantley", "Norah", "Caden", "Eliza", "Oscar", "Rose", "Abraham", "Rebecca", "Brady", "Michelle",
		"Sean", "Alaina", "Jake", "Catherine", "Tucker", "Londyn", "Nicolas", "Summer", "Mark", "Lila", "Amir",
		"Jayla", "Avery", "Katelyn", "King", "Daniela", "Gael", "Harmony", "Kenneth", "Alana", "Bradley", "Amaya",
		"Cayden", "Emerson", "Xander", "Julianna", "Graham", "Cecilia", "Rowan", "Izabella"]

		last_names=["Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson",
		"Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark",
		"Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez",
		"Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts",
		"Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
		"Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox",
		"Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders",
		"Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long",
		"Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant",
		"Alexander", "Russell", "Griffin", "Diaz", "Hayes"]
 
 
		first_name = names[r.randint(0,len(names)-1)]
		last_name = last_names[r.randint(0,len(last_names)-1)]
		ph_no = []
		for i in range(0, 10):
			ph_no.append(str(r.randint(0, 9)))
		ph_no = "".join(ph_no)

		age= r.randint(1,120)
		income = float(r.randint(1,100000))
		house_location = f'{{"x": {float(r.randint(1,100))}, "y": float(r.randint(1,100)) }}'
		return {"id":id, "first_name":first_name,"last_name":last_name, "ph_no":ph_no,"age":age,"income":income,"house_location":house_location}
 	




	def _make_flight_info(self, dataset):
		dataset_path = self._repo / dataset
		schema = pa.parquet.read_schema(dataset_path)
		metadata = pa.parquet.read_metadata(dataset_path)
		descriptor = pa.flight.FlightDescriptor.for_path(
			dataset.encode('utf-8')
		)
		endpoints = [pa.flight.FlightEndpoint(dataset, [self._location])]
		return pyarrow.flight.FlightInfo(schema,
										descriptor,
										endpoints,
										metadata.num_rows,
										metadata.serialized_size)

	def list_flights(self, context, criteria):
		for dataset in self._repo.iterdir():
			yield self._make_flight_info(dataset.name)

	def get_flight_info(self, context, descriptor):
		return self._make_flight_info(descriptor.path[0].decode('utf-8'))

	def do_put(self, context, descriptor, reader, writer):
		dataset = descriptor.path[0].decode('utf-8')
		dataset_path = self._repo / dataset
		data_table = reader.read_all()

	  #  pa.parquet.write_table(data_table, dataset_path)

	def do_get(self, context, ticket):
		#dataset = ticket.ticket.decode('utf-8')
		#dataset_path = self._repo / dataset
		
		return pa.flight.RecordBatchStream(self.dataset)

	def list_actions(self, context):
		return [
			("drop_dataset", "Delete a dataset."),
		]

	def do_action(self, context, action):
		if action.type == "drop_dataset":
			self.do_drop_dataset(action.body.to_pybytes().decode('utf-8'))
		else:
			raise NotImplementedError

	def do_drop_dataset(self, dataset):
		dataset_path = self._repo / dataset
		dataset_path.unlink()


if __name__ == '__main__':
	
	server = FlightServer()
	server._repo.mkdir(exist_ok=True)
	server.serve()






