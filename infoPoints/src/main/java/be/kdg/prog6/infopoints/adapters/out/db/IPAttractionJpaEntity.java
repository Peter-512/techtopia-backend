package be.kdg.prog6.infopoints.adapters.out.db;

import be.kdg.prog6.infopoints.domain.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table (name = "infopoint_attractions")
public class IPAttractionJpaEntity {
	@Id
	@JdbcTypeCode (Types.VARCHAR)
	private UUID attractionUUID;
	private String name;
	@Column (length = 1000)
	private String description;
	private String iconUrl;
	private String imageUrl;
	private int waitingTime;
	private int x;
	private int y;
	@Fetch (FetchMode.JOIN)
	@Enumerated (EnumType.STRING)
	@ElementCollection (targetClass = Tag.class)
	private List<Tag> tags;
	private int minHeight;
	private int minAge;
	@ColumnDefault ("true")
	private boolean isOpen;
}
