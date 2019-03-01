package com.ccbcfx.learn.configs;

import com.ccbcfx.learn.remote.dto.StaffDto;
import com.ccbcfx.learn.tables.pojos.Staff;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
public class OrikaConfig {
    @Bean
    public MapperFactory getFactory(){
        MapperFactory mapperFactory=new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new LocalDateConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeConverter());
        mapperFactory.getConverterFactory().registerConverter(new LocalTimeConverter());
        mapperFactory.classMap(StaffDto.class, Staff.class)
                .byDefault()
                .register();
        mapperFactory.classMap(Staff.class,StaffDto.class)
                .byDefault()
                .register();
        return mapperFactory;
    }

    private class LocalDateTimeConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {
        @Override
        public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType) {
            return LocalDateTime.from(source);
        }
        @Override
        public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType) {
            return LocalDateTime.from(source);
        }
    }
    private class LocalDateConverter extends BidirectionalConverter<LocalDate, LocalDate> {
        @Override
        public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType) {
            return LocalDate.from(source);
        }
        @Override
        public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType) {
            return LocalDate.from(source);
        }
    }
    private class LocalTimeConverter extends BidirectionalConverter<LocalTime, LocalTime> {
        @Override
        public LocalTime convertTo(LocalTime source, Type<LocalTime> destinationType) {
            return LocalTime.from(source);
        }
        @Override
        public LocalTime convertFrom(LocalTime source, Type<LocalTime> destinationType) {
            return LocalTime.from(source);
        }
    }
}
