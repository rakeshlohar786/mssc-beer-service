package guru.springframework.msscbeerservice.domain;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {

	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
	private UUID id;
	
	@Version
	private Long version;
	
	/** Rakesh note
	 * creation and updation timestamp is not wokring so used to CreatedDate and LastModified annotation
	 */
	
//	@CreationTimestamp
//	@Column(updatable = false)
	@CreatedDate
	@ColumnDefault("CURRENT_TIMESTAMP")
	Timestamp createdDate;
	//
	//@UpdateTimestamp
	@LastModifiedDate
	@ColumnDefault("CURRENT_TIMESTAMP")
	Timestamp updatedDate;
	
	
	private String beerName;
	private String beerStyle;
	
	@Column(unique=true)
	private String upc;
	
	private BigDecimal price;
	
	private Integer minOnHand;
	private Integer quantityToBrew;
	
}
