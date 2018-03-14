package total.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	SqlSessionTemplate template;

	public boolean addNewMember(Map<String, String> param) {
		int rst = template.insert("member.addMember", param);
		if (rst == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean loginMember(Map<String, String> param) {
		// TODO Auto-generated method stub
		Map map = template.selectOne("member.login", param);
		System.out.println(param);
		if (map != null) {
			return true;
		} else {
			return false;
		}
	}

	public List<Map> find(String id) {
		return template.selectList("member.friend", id);
	}

}
