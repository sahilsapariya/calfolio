from faker import Faker

# Create an instance of Faker
fake = Faker()

# Custom phone number format (e.g., Indian mobile number format)

for _ in range(30):
    custom_phone_number = fake.numerify('+91 ##########')  # 91 for country code, 10 digits for phone number
    print("Custom Fake Phone Number:", custom_phone_number)
