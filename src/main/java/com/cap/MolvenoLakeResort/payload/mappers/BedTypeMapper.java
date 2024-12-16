package com.cap.MolvenoLakeResort.payload.mappers;

import com.cap.MolvenoLakeResort.model.business.room.BedType;
import com.cap.MolvenoLakeResort.payload.request.room.BedTypeRequestDto;
import com.cap.MolvenoLakeResort.payload.response.room.BedTypeResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BedTypeMapper {
    public List<BedTypeResponseDto> mapBedTypeListToBedTypeResponseDtoList(List<BedType> bedTypeList) {

        List<BedTypeResponseDto> bedTypeResponseDtos = new ArrayList<>();

        for(BedType bedType: bedTypeList){
            BedTypeResponseDto bedTypeResponseDto = new BedTypeResponseDto();

            bedTypeResponseDto.setBedTypeName(bedType.getBedTypeName());
            bedTypeResponseDto.setBedTypeNameZh(bedType.getBedTypeNameZh());
            bedTypeResponseDto.setBedTypeId(bedType.getBedTypeId());

            bedTypeResponseDtos.add(bedTypeResponseDto);
        }
        return bedTypeResponseDtos;


    }

    public List<BedTypeResponseDto> mapBedTypeListToBedTypeResponseDtoList(List<BedType> bedTypeList, String lang) {

        List<BedTypeResponseDto> bedTypeResponseDtos = new ArrayList<>();

        for(BedType bedType: bedTypeList){
            BedTypeResponseDto bedTypeResponseDto = new BedTypeResponseDto();

            if ("zh".equalsIgnoreCase(lang)) {

                bedTypeResponseDto.setBedTypeName(bedType.getBedTypeNameZh());

            } else {
                bedTypeResponseDto.setBedTypeName(bedType.getBedTypeName());
            }
            bedTypeResponseDto.setBedTypeId(bedType.getBedTypeId());

            bedTypeResponseDtos.add(bedTypeResponseDto);
        }
        return bedTypeResponseDtos;


    }

    public BedType mapBedTypeRequestDtoToBedType(BedTypeRequestDto bedTypeRequestDto) {
        BedType bedType = new BedType();
        bedType.setBedTypeId(bedTypeRequestDto.getBedTypeId());
        bedType.setBedTypeName(bedTypeRequestDto.getBedTypeName());
        bedType.setBedTypeNameZh(bedTypeRequestDto.getBedTypeNameZh());
        return bedType;


    }

    public BedTypeResponseDto mapBedTypeToBedTypeResponseDto(BedType savedBedType) {
        BedTypeResponseDto bedTypeResponseDto = new BedTypeResponseDto();
        bedTypeResponseDto.setBedTypeId(savedBedType.getBedTypeId());
        bedTypeResponseDto.setBedTypeName(savedBedType.getBedTypeName());
        bedTypeResponseDto.setBedTypeNameZh(savedBedType.getBedTypeNameZh());
        return bedTypeResponseDto;

    }
}
