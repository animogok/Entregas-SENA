from typing import List
from Backend.router.Restaurant_info import Restaurant, restaurant_Base
from sqlalchemy.orm import Session


def get_restaurant_byID(db: Session, restaurant_id: int) -> Restaurant:
    """
    Retrieves a restaurant by its ID.

    :param db: Database session.
    :param restaurant_id: ID of the restaurant to retrieve.
    :return: The restaurant with the given ID, or None if not found.
    """
    return db.query(Restaurant).filter(Restaurant.id == restaurant_id).first()


def get_restaurant_byName(db: Session, restaurant_name: str) -> Restaurant:
    """
    Retrieves a restaurant by its name.

    :param db: Database session.
    :param restaurant_name: Name of the restaurant to retrieve.
    :return: The restaurant with the given name, or None if not found.
    """
    return db.query(Restaurant).filter(Restaurant.name == str(restaurant_name)).first()


def get_restaurants(db: Session, skip: int, limit: int = 100) -> List[Restaurant]:
    """
    Retrieves a list of restaurants with pagination.

    :param db: Database session.
    :param skip: Number of records to skip.
    :param limit: Maximum number of records to return. Defaults to 100.
    :return: List of restaurants, skipping the first 'skip' records and limiting to 'limit' records.
    """
    return db.query(Restaurant).offset(skip).limit(limit).all()


def create_restaurant(
    db: Session, restaurant: restaurant_Base, user_id: int
) -> Restaurant:
    """
    Creates a new restaurant.

    :param db: Database session.
    :param restaurant: Data of the restaurant to create.
    :param user_id: ID of the user who owns the restaurant.
    :return: The newly created restaurant.
    """

    db_restaurant = Restaurant(
        name=restaurant.name,
        number=restaurant.number,
        food_type=restaurant.food_type,
        address=restaurant.address,
        owner_id=user_id,
    )
    db.add(db_restaurant)
    db.commit()
    db.refresh(db_restaurant)
    return db_restaurant
