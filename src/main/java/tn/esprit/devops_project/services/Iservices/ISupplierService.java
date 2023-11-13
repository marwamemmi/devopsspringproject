package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Supplier;

import java.util.List;

public interface ISupplierService {

	List<Supplier> retrieveAllSuppliers();

	Supplier addSupplier(Supplier supplier);


	Supplier updateSupplier(Supplier supplier);

	Supplier retrieveSupplier(Long id);

}
