package com.example.blockfilesextension.mapper;

import com.example.blockfilesextension.model.Extension;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExtensionMapper {
    List<Extension> getAllExtensions(@Param("sessionId") String sessionId);
    Extension getExtensionByIndex(@Param("extensionIndex") int extensionIndex);
    //void insertExtension(Extension extension);
    //void updateExtension(Extension extension);
    //void deleteExtension(@Param("extensionIndex") int extensionIndex);
}
