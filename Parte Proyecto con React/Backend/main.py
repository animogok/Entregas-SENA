"""Module providing the use of the application for the backend"""

from fastapi import Depends, FastAPI
from fastapi.middleware.cors import CORSMiddleware
from .router import Restaurant_info

from Backend.SQL_APP import models
from Backend.SQL_APP.database import SessionLocal, engine

models.Base.metadata.create_all(bind=engine)


def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


app = FastAPI()
app.include_router(Restaurant_info.router_restaurant_info)

origins = ["http://localhost:5173/restaurant-form", "http://localhost:5173"]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/")
async def read_root():
    return {"message": "Welcome to the backend API"}
