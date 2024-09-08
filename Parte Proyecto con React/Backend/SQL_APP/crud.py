from sqlalchemy.orm import Session


"""GET METHODS"""


def get_restaurant_byID(db: Session, restaurant_id: int):
    from Backend.router.Restaurant_info import (
        Restaurant,
    )  # Importación dentro de la función

    return db.query(Restaurant).filter(Restaurant.id == restaurant_id).first()


def get_restautant_byName(db: Session, restaurant_name: str):
    from .models import Restaurant  # Importación dentro de la función

    return db.query(Restaurant).filter(Restaurant.name == str(restaurant_name)).first()


def get_restaurants(db: Session, skip: int, limit: int = 100):
    from .models import (
        Restaurant,
    )  # Importación dentro de la función  # Importación dentro de la función

    return db.query(Restaurant).offset(skip).limit(limit).all()


"""CREATE METHODS"""

from ..router.Restaurant_info import restaurant_Base


def create_restaurant(db: Session, restaurant: restaurant_Base, user_id: int):
    from .models import Restaurant

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
