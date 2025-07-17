package org.example.testClasses;

import org.example.features.TicketCreateAndFilterFeature;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClassForTicketCreateAndFilter {
    TicketCreateAndFilterFeature ticketFilter;

    @BeforeTest
    public void init() throws Exception {
        ticketFilter = new TicketCreateAndFilterFeature();
    }

    @Test(priority = 5)
    public void testHelpdesk() throws InterruptedException {
        ticketFilter.helpdesk();
    }

    @Test(priority = 6)
    public void testTicket() throws InterruptedException {
        ticketFilter.ticket();
    }

    @Test(priority = 7)
    public void testAddTicket() throws InterruptedException {
        ticketFilter.addTicketButton();
    }

    @Test(priority = 8)
    public void testTicketCreation() throws InterruptedException {
        ticketFilter.newTicketCreation();
    }

    @Test(priority = 9)
    public void testTicketFilter() throws InterruptedException {
        ticketFilter.FilteringTicket();
    }
}
