from typing import Annotated
from Backend.SQL_APP.database import SessionLocal
from fastapi import APIRouter, Form, HTTPException
from pydantic import BaseModel


router_restaurant_info = APIRouter()

"""
Here will be some pydantic models that will help to the correct validation and the correct format
"""


class restaurant_Base(BaseModel):
    name: str
    number: str
    food_type: str
    address: str


class Restaurant(restaurant_Base):

    owner_id: int

    class Config:
        orm_mode = True


@router_restaurant_info.post("/restaurant_registration/")
def restaurant_registration(data: Annotated[restaurant_Base, Form()]):
    from Backend.SQL_APP import crud

    restaurant_existence = crud.get_restautant_byName(
        db=SessionLocal(), restaurant_name=str(data.name)
    )

    if restaurant_existence:
        raise HTTPException(status_code=400, detail="Restaurant already exists")

    if len(data.number) < 5 or len(data.number) > 15:
        raise HTTPException(status_code=400, detail="Incorrect number lenght")
    crud.create_restaurant(db=SessionLocal(), restaurant=data, user_id=2)

    return {"Succesfull": "Your restaurant have been created"}


@router_restaurant_info.get("/restaurants/", response_model=list[Restaurant])
def get_restaurants(skip: int = 0, limit: int = 100):
    from Backend.SQL_APP import crud

    restaurants = crud.get_restaurants(db=SessionLocal(), skip=skip, limit=limit)
    return restaurants
