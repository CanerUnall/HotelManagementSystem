package com.cap.MolvenoLakeResort.service.business.room;

import com.cap.MolvenoLakeResort.model.business.room.BedType;
import com.cap.MolvenoLakeResort.payload.request.room.BedTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.BedTypeResponseDto;

import java.util.List;

public interface BedTypeService {
    List<BedTypeResponseDto> getAllBedType();

    List<BedTypeResponseDto> getAllBedType(String lang);

    String deleteBedTypeById(Long id);

    BedType saveBedType(BedType bedType);

    String deleteBedTypeByBedTypeName(String bedTypeName);

    BedTypeResponseDto saveBedTypeByBedTypeName(BedTypeRequestDto bedTypeRequestDto);

    BedTypeResponseDto editBedTypeByBedTypeName(BedTypeRequestDto bedTypeRequestDto, String bedTypeName);

    List<BedType> getBedTypeList(List<String> stringBedTypeList);

    boolean canDeleteBedType(Long bedTypeId);
}
