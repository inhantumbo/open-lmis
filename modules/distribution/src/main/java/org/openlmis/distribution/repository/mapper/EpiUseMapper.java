/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 *  This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 *  You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org.
 */

package org.openlmis.distribution.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.distribution.domain.EpiUse;
import org.openlmis.distribution.domain.EpiUseLineItem;
import org.springframework.stereotype.Repository;

@Repository
public interface EpiUseMapper {

  @Insert({"INSERT INTO epi_use_line_items (epiUseId, productGroupId, productGroupName, stockAtFirstOfMonth, received, ",
    "distributed, loss, stockAtEndOfMonth, expirationDate, createdBy) VALUES (#{epiUseId}, #{productGroup.id}, #{productGroup.name}, #{stockAtFirstOfMonth},",
    " #{received}, #{distributed}, #{loss}, #{stockAtEndOfMonth}, #{expirationDate}, #{createdBy})"})
  @Options(useGeneratedKeys = true)
  public void insertLineItem(EpiUseLineItem epiUseLineItem);

  @Insert({"INSERT INTO epi_use (distributionId, facilityId, createdBy) VALUES (#{distributionId}, #{facilityId}, #{createdBy})"})
  @Options(useGeneratedKeys = true)
  public void insert(EpiUse epiUse);

  @Select({"SELECT * FROM epi_use WHERE id = #{id}"})
  public EpiUse getById(EpiUse epiUse);

  @Select({"SELECT * FROM epi_use_line_items WHERE id = #{id}"})
  @Results(value = {
    @Result(property = "productGroup.id", column = "productGroupId"),
    @Result(property = "productGroup.name", column = "productGroupName")
  })
  public EpiUseLineItem getLineItemById(EpiUseLineItem epiUseLineItem);

  @Update({"UPDATE epi_use_line_items SET received = #{received}, distributed = #{distributed}, loss = #{loss},",
    "stockAtFirstOfMonth = #{stockAtFirstOfMonth}, stockAtEndOfMonth = #{stockAtEndOfMonth}, expirationDate = #{expirationDate},",
    "modifiedBy = #{modifiedBy} WHERE id = #{id}"})
  public void updateLineItem(EpiUseLineItem epiUseLineItem);
}