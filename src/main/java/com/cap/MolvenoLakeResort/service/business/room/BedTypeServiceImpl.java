package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.BedType;
import com.cap.MolvenoLakeResort.payload.mappers.BedTypeMapper;
import com.cap.MolvenoLakeResort.payload.request.room.BedTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.BedTypeResponseDto;
import com.cap.MolvenoLakeResort.repository.business.room.BedTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BedTypeServiceImpl implements BedTypeService{


    private final BedTypeRepository bedTypeRepository;
    private final BedTypeMapper bedTypeMapper;

    @Autowired

    public BedTypeServiceImpl(BedTypeRepository bedTypeRepository, BedTypeMapper bedTypeMapper) {
        this.bedTypeRepository = bedTypeRepository;
        this.bedTypeMapper = bedTypeMapper;
    }

    @Override
    public List<BedTypeResponseDto> getAllBedType() {
        List<BedType> bedTypeList  =bedTypeRepository.findAll();

        List<BedTypeResponseDto> bedTypeResponseDtos = bedTypeMapper.mapBedTypeListToBedTypeResponseDtoList(bedTypeList);
        return bedTypeResponseDtos;
    }
    @Override
    public List<BedTypeResponseDto> getAllBedType(String lang) {
        List<BedType> bedTypeList  =bedTypeRepository.findAll();

        List<BedTypeResponseDto> bedTypeResponseDtos = bedTypeMapper.mapBedTypeListToBedTypeResponseDtoList(bedTypeList,lang);
        return bedTypeResponseDtos;
    }

    @Override
    public String deleteBedTypeById(Long id) {
        if (bedTypeRepository.findById(id).isPresent()) {
            bedTypeRepository.deleteById(id);
            return "Bed Type deleted successfully!!";
        } else {
            return "Bed Type Not Found";
        }
    }

    @Override
    public BedType saveBedType(BedType bedType) {
        Optional<BedType> optionalBedType = bedTypeRepository.findById(bedType.getBedTypeId());

        if (optionalBedType.isPresent()) {
            throw new RuntimeException("Bed Type is exist");

        }
        return bedTypeRepository.save(bedType);


    }
    @Override
    public String deleteBedTypeByBedTypeName(String bedTypeName) {
        Optional<BedType> bedType = bedTypeRepository.findByBedTypeName(bedTypeName);

        if(bedType.isPresent()){
            try {
                bedTypeRepository.deleteById(bedType.get().getBedTypeId());
                return "You cannot delete. This bed type is used in some rooms. Please delete them first.!!";
            }catch (Exception e){
                return "Bed Type deleted successfully!!";
            }

        }else{
            return "Bed Type Not Found! ";
        }
    }
    @Override
    public BedTypeResponseDto saveBedTypeByBedTypeName(BedTypeRequestDto bedTypeRequestDto) {

        BedType bedType = bedTypeMapper.mapBedTypeRequestDtoToBedType(bedTypeRequestDto);
        BedType savedBedType = bedTypeRepository.save(bedType);
        BedTypeResponseDto bedTypeResponseDto = bedTypeMapper.mapBedTypeToBedTypeResponseDto(savedBedType);
        return bedTypeResponseDto;

    }
    @Override
    public BedTypeResponseDto editBedTypeByBedTypeName(BedTypeRequestDto bedTypeRequestDto, String bedTypeName) {
        BedType bedtype = bedTypeRepository.findByBedTypeName(bedTypeName).get();
        BedType bedTypeToRegister = bedTypeMapper.mapBedTypeRequestDtoToBedType(bedTypeRequestDto);
        bedTypeToRegister.setBedTypeName(bedtype.getBedTypeName());
        BedType savedBedType= bedTypeRepository.save(bedTypeToRegister);

        return bedTypeMapper.mapBedTypeToBedTypeResponseDto(savedBedType);
    }

    @Override
    public List<BedType> getBedTypeList(List<String> stringBedTypeList) {
        List<BedType> bedTypeList1 = new ArrayList<>();

        for (String bedTypeName : stringBedTypeList) {
            if (bedTypeRepository.findByBedTypeName(bedTypeName).isPresent()) {
                bedTypeList1.add(bedTypeRepository.findByBedTypeName(bedTypeName).get());
            }

        }
        return bedTypeList1;

    }

    @Override
    public boolean canDeleteBedType(Long bedTypeId) {
        BedType bedType = bedTypeRepository.findById(bedTypeId).orElse(null);
        if (bedType != null && bedType.getRoomList().isEmpty()) {
            return true;
        }
        return false;
    }
}


