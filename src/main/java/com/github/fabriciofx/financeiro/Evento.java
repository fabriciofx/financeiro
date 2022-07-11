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
package com.github.fabriciofx.financeiro;

import java.time.LocalDate;
import java.util.List;

public interface Evento {
    /**
     * O tipo de evento que ocorreu.
     * @return O tipo de evento
     */
    TipoEvento tipoEvento();

    /**
     * Quando o evento ocorreu.
     * @return A data quando o evento ocorreu
     */
    LocalDate ocorrido();

    /**
     * Quando o evento foi observado.
     * @return A data quando o evento foi observado
     */
    LocalDate observado();

    /**
     * O cliente que fez aquele evento.
     * @return O cliente que fez o evento
     */
    Cliente cliente();

    /**
     * Faz um lançcamento em um evento.
     * @param lancamento Um lançamento
     */
    void faz(Lancamento lancamento);

    /**
     * Processa um evento de acordo com os lançamentos.
     */
    void processa();

    /**
     * Retorna todos os lançamentos.
     * @return Todos os lançcamentos
     */
    List<Lancamento> lancamentos();
}
