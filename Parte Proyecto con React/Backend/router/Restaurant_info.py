from typing import Annotated
from Backend.SQL_APP.database import SessionLocal
from fastapi import APIRouter, Form, HTTPException
from pydantic import BaseModel


router_restaurant_info = APIRouter()

"""
This module defines the API endpoints for restaurant information.
"""


class RestaurantBase(BaseModel):
    """
    Represents the base attributes of a restaurant.

    Attributes:
        name (str): The name of the restaurant.
        number (str): The phone number of the restaurant.
        food_type (str): The type of food served by the restaurant.
        address (str): The address of the restaurant.
    """

    name: str
    number: str
    food_type: str
    address: str


class Restaurant(RestaurantBase):
    """
    Represents a restaurant with an owner.

    Attributes:
        owner_id (int): The ID of the restaurant owner.
    """

    owner_id: int

    class Config:
        """
        Configuration for the Restaurant model.
        """

        orm_mode = True


@router_restaurant_info.post("/restaurant_registration/")
def restaurant_registration(data: Annotated[RestaurantBase, Form()]):
    """
    Registers a new restaurant.

    Args:
        data (RestaurantBase): The restaurant data to be registered.

    Raises:
        HTTPException: If the restaurant already exists or the phone number is invalid.

    Returns:
        dict: A success message indicating that the restaurant has been created.
    """
    from Backend.SQL_APP import crud

    restaurant_existence = crud.get_restaurant_by_name(
        db=SessionLocal(), restaurant_name=str(data.name)
    )

    if restaurant_existence:
        raise HTTPException(status_code=400, detail="Restaurant already exists")

    if len(data.number) < 5 or len(data.number) > 15:
        raise HTTPException(status_code=400, detail="Incorrect phone number length")
    crud.create_restaurant(db=SessionLocal(), restaurant=data, user_id=2)

    return {"Success": "Your restaurant has been created"}


@router_restaurant_info.get("/restaurants/", response_model=list[Restaurant])
def get_restaurants(skip: int = 0, limit: int = 100):
    """
    Retrieves a list of restaurants.

    Args:
        skip (int): The number of restaurants to skip. Defaults to 0.
        limit (int): The maximum number of restaurants to return. Defaults to 100.

    Returns:
        list[Restaurant]: A list of restaurants.
    """
    from Backend.SQL_APP import crud

    restaurants = crud.get_restaurants(db=SessionLocal(), skip=skip, limit=limit)
    return restaurants
