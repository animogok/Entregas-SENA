import datetime
from sqlalchemy import Boolean, Column, Integer, String, ForeignKey, DateTime
from sqlalchemy.orm import relationship

from Backend.SQL_APP.database import Base


class User(Base):
    """
    Represents a user in the system.

    Attributes:
        id (int): Unique identifier for the user.
        name (str): User's name.
        surname (str): User's surname.
        email (str): User's email address.
        number (str): User's phone number.
        password (str): User's password (hashed for security).
        # is_active (bool): Whether the user is active or not (default=False).
        # date_joined (datetime): Date and time when the user joined the system (default=datetime.datetime.now).

    Relationships:
        restaurant (Restaurant): The restaurant owned by this user.
    """

    __tablename__ = "users"

    id = Column(Integer, primary_key=True)
    name = Column(String(50))  # Specify length
    surname = Column(String(50))  # Specify length
    email = Column(String(100))  # Specify length
    number = Column(String(50))
    password = Column(String(100))  # Specify length for hashed password
    is_active = Column(Boolean, default=False)
    date_joined = Column(DateTime, default=datetime.datetime.now)

    # Relationship with the Restaurant table
    restaurant = relationship("Restaurant", back_populates="owner")


class Restaurant(Base):
    """
    Represents a restaurant in the system.

    Attributes:
        id (int): Unique identifier for the restaurant.
        name (str): Restaurant's name (unique).
        number (int): Restaurant's phone number.
        food_type (str): Type of food served by the restaurant (unique).
        address (str): Restaurant's address (unique).
        owner_id (int): Foreign key referencing the User who owns this restaurant.

    Relationships:
        owner (User ): The user who owns this restaurant.
    """

    __tablename__ = "restaurant"

    id = Column(Integer, primary_key=True)
    name = Column(String(100), unique=True)  # Specify length
    number = Column(Integer)
    food_type = Column(String(50), unique=True)  # Specify length
    address = Column(String(200), unique=True)  # Specify length
    owner_id = Column(Integer, ForeignKey("users.id"))  # Foreign key

    # Relationship with the User table
    owner = relationship("User ", back_populates="restaurant")
