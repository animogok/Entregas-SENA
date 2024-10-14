"""
Module providing the use of the application for the backend
"""

# Importing necessary modules from FastAPI for building the API
from fastapi import Depends, FastAPI
from fastapi.middleware.cors import CORSMiddleware

# Importing the router for restaurant information
from .router import Restaurant_info

# Importing models and database engine from SQL_APP module
from Backend.SQL_APP import models
from Backend.SQL_APP.database import SessionLocal, engine

# Creating all tables in the database based on the models
models.Base.metadata.create_all(bind=engine)


# Function to get a database session
def get_db():
    # Creating a new database session
    db = SessionLocal()
    try:
        # Yielding the database session
        yield db
    finally:
        # Closing the database session
        db.close()


# Creating a new FastAPI application
app = FastAPI()

# Including the router for restaurant information
app.include_router(Restaurant_info.router_restaurant_info)

# Defining the allowed origins for CORS (Cross-Origin Resource Sharing)
origins = ["http://localhost:5173/restaurant-form", "http://localhost:5173"]

# Adding CORS middleware to the application
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# Root endpoint of the API
@app.get("/")
async def read_root():
    # Returning a welcome message
    return {"message": "Welcome to the backend API"}
