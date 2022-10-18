package guru.springframework.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.*;
import guru.springframework.msscbeerservice.domain.*;

public interface BeerRepository extends PagingAndSortingRepository<Beer,UUID>
{

}
