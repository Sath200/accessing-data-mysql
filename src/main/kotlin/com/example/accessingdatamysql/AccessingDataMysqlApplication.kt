package com.example.accessingdatamysql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@SpringBootApplication
class AccessingDataMysqlApplication

fun main(args: Array<String>) {
	runApplication<AccessingDataMysqlApplication>(*args)
}

@Entity
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private var id: Int? = null

	private var name: String? = null

	private var email: String? = null

	fun getId(): Int? {
		return id
	}

	fun setId(id: Int?) {
		this.id = id
	}

	fun getName(): String? {
		return name
	}

	fun setName(name: String?) {
		this.name = name
	}

	fun getEmail(): String? {
		return email
	}

	fun setEmail(email: String?) {
		this.email = email
	}
}

interface UserRepository : CrudRepository<User?, Int?>{

}

/*@Controller
@RequestMapping(path = ["/demo"])
class MainController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private val userRepository: UserRepository? = null

	@PostMapping(path = ["/add"])
	@ResponseBody
	fun addNewUser(@RequestParam name: String?, @RequestParam email: String?): String{
		val n = User()
		n.setName(name)
		n.setEmail(email)
		userRepository!!.save(n)
		return "Saved"
	}

	@get:ResponseBody
	@get:GetMapping(path = ["/all"])
	val allUsers: Iterable<User?>
		get() =  userRepository!!.findAll()
}
 */

@RestController
class MainController{
	@Autowired
	private val userRepository: UserRepository? = null
	@PostMapping("/add")
	fun addNewUser(@RequestParam name: String?, @RequestParam email: String?): String{
		val n=User()
		n.setName(name)
		n.setEmail(email)
		userRepository!!.save(n)
		return "Saved"
	}

	@GetMapping("/all")
	fun getUsers(): MutableIterable<User?> {
		return userRepository!!.findAll()
	}

	@DeleteMapping("/delete")
	fun deleteUser(@RequestParam id:Int){
           return userRepository!!.deleteById(id)
	}
}












