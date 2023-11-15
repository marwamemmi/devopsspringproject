package tn.esprit.devops_project.services;

import tn.esprit.devops_project.entities.Stock;

import java.util.List;

public interface IStockService {

 
    Stock retrieveStock(Long id);
    List<Stock> retrieveAllStock();

}
