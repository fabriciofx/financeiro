package com.github.fabriciofx.financeiro.dominio.infraestrutura;

import java.time.LocalDate;

public interface TemporalCollection<E> {
	public E get(LocalDate when);

	public void put(LocalDate when, E item);

	public E get();

	public void put(E item);
}
