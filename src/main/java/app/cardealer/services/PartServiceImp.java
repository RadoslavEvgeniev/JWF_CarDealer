package app.cardealer.services;

import app.cardealer.entites.Part;
import app.cardealer.models.binding.PartCreateBindingModel;
import app.cardealer.models.view.PartQuantityViewModel;
import app.cardealer.models.view.PartViewModel;
import app.cardealer.repositories.PartRepository;
import app.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PartViewModel> extractParts() {
        List<Part> partsFromDb = this.partRepository.findAll();
        List<PartViewModel> parts = new ArrayList<>();

        for (Part part : partsFromDb) {
            PartViewModel partViewModel = this.modelMapper.map(part, PartViewModel.class);

            parts.add(partViewModel);
        }

        return parts;
    }

    @Override
    public PartViewModel extractPartById(Long id) {
        Part partFromDb = this.partRepository.findById(id).orElse(null);
        if (partFromDb == null) {
            return null;
        }

        PartViewModel part = this.modelMapper.map(partFromDb, PartViewModel.class);

        return part;
    }

    @Override
    public void removePartById(Long id) {
        this.partRepository.deleteById(id);
    }

    @Override
    public PartQuantityViewModel extractPartQuantityById(Long id) {
        Part partFromDb = this.partRepository.findById(id).orElse(null);
        if (partFromDb == null) {
            return null;
        }

        PartQuantityViewModel part = this.modelMapper.map(partFromDb, PartQuantityViewModel.class);

        return part;
    }

    @Override
    public void editPart(Long id, PartCreateBindingModel partBindingModel) {
        Part partFromDb = this.partRepository.findById(id).orElse(null);
        if (partFromDb == null) {
            return;
        }

        partFromDb.setPrice(partBindingModel.getPrice());
        partFromDb.setQuantity(partBindingModel.getQuantity());

        this.partRepository.save(partFromDb);
    }


}
