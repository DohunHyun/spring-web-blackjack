package kr.ac.cnu.web.repository;

import kr.ac.cnu.web.model.Menu;
import kr.ac.cnu.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by rokim on 2018. 5. 25..
 */
public interface UserRepository extends JpaRepository<User, String>{


}
