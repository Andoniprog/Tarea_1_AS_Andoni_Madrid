/*
 * Copyright (c) 2026. Arquitectura de Sistemas, DISC, UCN, Antofagasta.
 */
package cl.ucn.disc.arqsist.library.service;
import java.time.LocalDate;


public class LoanPolicy {
    public static final int DUE_DAYS = 21;

    public static final double FEE_PER_DAY = 1.0;

    private LoanPolicy() {
        // No instantiation
    }
    
    /**
     * Computes the due date of a loan.
     *
     * @param loanDate the date the loan starts.
     * @return the loan date plus {@link #DUE_DAYS} days.
     */
    public static LocalDate dueDate(LocalDate loanDate) {
        return loanDate.plusDays(DUE_DAYS);
    } 
}
