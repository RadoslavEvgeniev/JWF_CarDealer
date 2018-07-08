package app.cardealer.services;

import app.cardealer.entites.Part;
import app.cardealer.models.binding.PartCreateBindingModel;
import app.cardealer.repositories.PartRepository;
import app.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImp implements PartService {

    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImp(PartRepository partRepository, ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void importPart(PartCreateBindingModel partBindingModel) {
        Part part = new Part();
        part.setName(partBindingModel.getName());
        part.setPrice(partBindingModel.getPrice());
        part.setQuantity(partBindingModel.getQuantity());
        part.setSupplier(this.supplierRepository.getOne(partBindingModel.getSupplierId()));

        this.partRepository.save(part);
    }
}
