package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Invoice;

import java.util.Date;
import java.util.List;

public interface IInvoiceService {
	List<Invoice> retrieveAllInvoices();


	Invoice retrieveInvoice(Long id);
	
	void assignOperatorToInvoice(Long idOperator, Long idInvoice);

	float getTotalAmountInvoiceBetweenDates(Date startDate, Date endDate);
}
