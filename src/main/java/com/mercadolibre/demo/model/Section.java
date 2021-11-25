package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Getter
@Setter
@Entity
@Table(name = "section")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_code")
    private Long idSection;
	
    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "category")
    private String category;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ware_house", nullable = false)
    private WareHouse WareHouse;

    
    
    public Section(Long capacity, String category, WareHouse WareHouse) {
        this.capacity = capacity;
        this.category = category;
        this.WareHouse = WareHouse;
    }



	public Section() {
		super();
	}
}
