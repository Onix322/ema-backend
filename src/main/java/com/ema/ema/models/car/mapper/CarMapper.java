package com.ema.ema.models.car.mapper;

import com.ema.ema.models.car.Car;
import com.ema.ema.models.car.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface CarMapper {

  CarDto INSTANCE = Mappers.getMapper(CarDto.class);

  Car fromDto(CarDto dto);

  CarDto toDto(Car car);
}
