package dev.example.datageneratormicroservice.web.mapper;

public interface IMappable<E, D>{

    E toEntity(D dto);

    D toDto(E entity);
}
