package com.soho.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {

	public static void main(String[] args) {

		// 1. 通过Stream的静态工厂方法生产stream.
		// ***********************************************
		// 1.1 static <T> Stream<T> of(T... values)
		Stream<Integer> intStream = Stream.of(1, 2, 3, 5);
		System.out.println(intStream.reduce((p1, p2) -> p1 + p2));

		// 1.2 static <T> Stream<T> generate(Supplier<T> s)

		Stream<Double> dStream = Stream.generate(Math::random);
		dStream.limit(10).filter(d -> d < 0.5).forEach(System.out::println);

		// 1.3 iterate迭代生成流
		Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 2).limit(6);
		stream2.forEach(System.out::println);

		// 2. 数组生成流
		Integer[] nums = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Stream<Integer> stream3 = Arrays.stream(nums);
		stream3.forEach(System.out::println);

		// 3. List生成流
		List<String> list = new ArrayList<>();
		list.add("hello world");
		Stream<String> stream4 = list.stream();
		stream4.forEach(System.out::println);

		// 4.将文件每一行转化成流

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("D:\\test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stream<String> lineStream = reader.lines();
		lineStream.forEach(System.out::println);

		/**
		 * sorted操作
		 */
		List<String> cities = Arrays.asList("Milan", "london", "San Francisco", "Tokyo", "New Delhi");

		// 初始顺序
		System.out.println(cities);

		// 字母顺序，忽略大小写
		cities.sort(String.CASE_INSENSITIVE_ORDER);
		System.out.println(cities);

		cities.sort(Comparator.naturalOrder());
		System.out.println(cities);

		// 排序器用在stream中
		cities.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println(cities);

		// List<Object>按字段排序,如按年龄排序
		Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
		Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
		Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
		Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
		Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
		Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
		Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
		Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
		Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
		Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");

		List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
		employees.sort(Comparator.comparing(Employee::getAge)
				                 .thenComparing(Employee::getGender).reversed());
		employees.forEach(System.out::println);

		System.out.println("--------------------------------------");
		employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed().thenComparing(Employee::getGender))
				.forEach(System.out::println);

		System.out.println("---自定义比较器<匿名内部类实现>------------------------");
		
		//1:匿名内部类实现比较器
		employees.sort( new Comparator<Employee>() {
			@Override
			public int compare(Employee emp1, Employee emp2) {
				if(emp1.getAge() == emp2.getAge()) {
					return 0;
				}
				return emp1.getAge()-emp2.getAge() >0 ? -1:1 ;
			}
		});
		
		employees.forEach(System.out::println);
		
		
		System.out.println("---自定义比较器<lambda函数实现>------------------------");
		//lambda函数实现比较器
		employees.sort((emp1,emp2)->{
			
			if(emp1.getAge() == emp2.getAge()) {
				return 0;
			}
			return emp1.getAge()-emp2.getAge() >0 ? 1:-1 ;
		});
		
		employees.forEach(System.out::println);
		
		System.out.println("---filter操作 and------------------------");
		List<Employee> filtered = employees.stream()
				.filter(Employee.ageGreaterThan70 .and(Employee.genderM))
				.collect(Collectors.toList());
		
		filtered.forEach(System.out::println);
		
		System.out.println("---filter操作 or------------------------");
		List<Employee> filtered1 = employees.stream()
				.filter(Employee.ageGreaterThan70 .or(Employee.genderM))
				.collect(Collectors.toList());
		
		filtered1.forEach(System.out::println);
		
		//查找和匹配
		
		boolean isExistAgeThan70 = employees.stream().anyMatch(Employee.ageGreaterThan70);
		System.out.println("isExistAgeThan70:" + isExistAgeThan70);
		
		boolean isExistAgeThan10 = employees.stream().allMatch(e -> e.getAge() > 10);
		System.out.println("isExistAgeThan10:" + isExistAgeThan10);
		
		boolean isExistAgeLess18 = employees.stream().noneMatch(e -> e.getAge() < 18);
		System.out.println("isExistAgeLess18:" + isExistAgeLess18);
		
		//从列表中按照顺序查找第一个年龄大于40的员工
		Optional<Employee> emp= employees.stream().filter( e-> e.getAge() > 90 )
				.findFirst();
		System.out.println(emp.isPresent());
		
		//复杂对象的归约操作
		
		Optional<Integer> maxYear=employees.stream().map(Employee::getAge).max(( a,b )->{
			return a > b ? 1 : -1;
		});
		System.out.println("maxYear:" + maxYear.get());
		
		Integer sumI= employees.stream().map(Employee::getAge).reduce((total,e )-> total+e ).get();
//		Integer sumI= employees.stream().map(Employee::getAge).reduce(0, Integer::sum );
		System.out.println("sumI:" + sumI);
		
		//收集到map
		Map<String, Integer> toMap = Stream.of(
			    "Monkey", "Lion", "Giraffe", "Lemur", "Lion"
			)
			.distinct()
			.collect(Collectors.toMap(
			       Function.identity(),   //元素输入就是输出，作为key
			       s -> (int) s.chars().distinct().count()// 输入元素的不同的字母个数，作为value
			));
		
		System.out.println("toMap:" + toMap);
		
		//分组收集
		Map<Character, List<String> > groupingByList  = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
		      .collect(Collectors.groupingBy(s->s.charAt(0)));
		System.out.println("groupingByList:" + groupingByList);
		
		Map<String, Long > groupingByList1 =  Stream.of(
			    "Monkey", "Lion", "Giraffe", "Lemur", "Lion"
			)
			.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		
		System.out.println("groupingByList1:" + groupingByList1);
	}
}
