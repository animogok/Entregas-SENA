import datetime
from sqlalchemy import Boolean, Column, Integer, String, ForeignKey, DateTime
from sqlalchemy.orm import relationship

from Backend.SQL_APP.database import Base


class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True)
    name = Column(String(50))  # Specify length
    surname = Column(String(50))  # Specify length
    email = Column(String(100))  # Specify length
    number = Column(String(50))
    password = Column(String(100))  # Specify length for hashed password
    # is_active = Column(Boolean, default=False)
    # date_joined = Column(DateTime, default=datetime.datetime.now)

    # Relación con la tabla Restaurant
    restaurant = relationship("Restaurant", back_populates="owner")


class Restaurant(Base):
    __tablename__ = "restaurant"

    id = Column(Integer, primary_key=True)
    name = Column(String(100), unique=True)  # Specify length
    number = Column(Integer)
    food_type = Column(String(50), unique=True)  # Specify length
    address = Column(String(200), unique=True)  # Specify length
    owner_id = Column(Integer, ForeignKey("users.id"))  # Clave foránea

    # Relación con la tabla User
    owner = relationship("User", back_populates="restaurant")
