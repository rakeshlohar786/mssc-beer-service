package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

  private static final long serialVersionId = 324534543543543L;
	
  @Null
  private UUID id;
  
  @Null
  private Integer version;
  
  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
  private OffsetDateTime createdDate;
  
  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
  private OffsetDateTime lastModifiedDate;
  
  @NotBlank
  private String beerName;
  
  @NotNull
  private BeerStyleEnum beerStyle;
  
  @NotNull
  private String upc;
  
  
  @JsonFormat(shape=JsonFormat.Shape.STRING)
  @Positive
  @NotNull
  private BigDecimal price;
  
  private Integer quantityOnHand;
  
	
}
