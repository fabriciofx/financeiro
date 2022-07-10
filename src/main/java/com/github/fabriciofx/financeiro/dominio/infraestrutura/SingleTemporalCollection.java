/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2015-2022 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.financeiro.dominio.infraestrutura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleTemporalCollection<E> implements TemporalCollection<E> {
	private Map<LocalDate, E> contents = new HashMap<LocalDate, E>();
	private List<LocalDate> milestoneCache;

	@Override
	public E get(LocalDate when) {
		for (LocalDate thisDate : milestones()) {
			if (thisDate.isBefore(when) || thisDate.equals(when))
				return contents.get(thisDate);
		}

		throw new IllegalArgumentException("no records that early");
	}

	@Override
	public void put(LocalDate at, E item) {
		contents.put(at, item);
		clearMilestoneCache();
	}

	/**
	 * a list of all the dates where the value changed, return in order latest
	 * first.
	 */
	private List<LocalDate> milestones() {
		if (milestoneCache == null)
			calculateMilestones();
		return milestoneCache;
	}

	private void calculateMilestones() {
		milestoneCache = new ArrayList<LocalDate>(contents.size());
		milestoneCache.addAll(contents.keySet());
		Collections.sort(milestoneCache, Collections.reverseOrder());
	}

	private void clearMilestoneCache() {
		milestoneCache = null;
	}

	@Override
	public E get() {
		return get(LocalDate.now());
	}

	@Override
	public void put(E item) {
		put(LocalDate.now(), item);
	}

	public SingleTemporalCollection<E> copy() {
		SingleTemporalCollection<E> result = new SingleTemporalCollection<E>();
		result.contents.putAll(this.contents);
		
		return result;
	}
}
