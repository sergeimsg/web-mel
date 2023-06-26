package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;

public class NewsDAO implements INewsDAO {

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 0;

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
					"root", "Irina1983");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM news ORDER BY news_date DESC");

			while (rs.next() && i < count) {

				i++;
//			System.out.println(rs.getInt("id")+"  "+rs.getString("title") + " "+ rs.getString("brief_news") +
//					rs.getString("content") + rs.getString("news_date")+  rs.getString("newscol"));

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("newscol")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

//		result.add(new News(1, "New ID Buzz will hit the market", "Price gouging ID Buzz buyers would be big mistake",
//				"Price gouging ID Buzz buyers would be big mistake\"\r\n"
//						+ "+ \"Pablo Di Si, head of Volkswagen in North America, says dealer price gouging on the new three-row ID Buzz electric bus would not be smart.",
//				"06/03/2023", ""));
//
//		result.add(new News(2, "Ландыши весной",
//				"Ла́ндыш ма́йский (лат. Convallária majális) — вид травянистых цветковых растений, распространённый в регионах с умеренным климатом Северного полушария. ",
//				"Травянистое многолетнее растение 15—30 см высотой. Подземное корневище горизонтальное ползучее, не толще гусиного пера, несёт близ верхушки несколько бледных небольших низовых листьев, полускрытых в земле. Корни мелкие, многочисленные, мочковатые.\r\n"
//						+ "\r\n"
//						+ "Надземные побеги укороченные. За низовыми листьями следуют два (редко три) больших, совершенно цельных широколанцетных (или продолговато-эллиптических) заострённых прикорневых листа, между которыми на верхушке корневища находится крупная почка. Из угла низового листа, обхватывающего снизу оба зелёных, выступает цветоносный стебель, несущий кисть из 6—20 цветков, обращённых преимущественно в одну сторону. Цветоносный стебель безлистный либо несёт листья лишь под соцветием; редко — с нитевидными листьями. Ароматные цветки грациозно поникают. Время цветения — с мая по июнь.",
//				"04/06/2023", "images/newsImages/news2.jpg"));
//		result.add(new News(3, "Скандинавская ходьба",
//				"Скандина́вская ходьба́ (англ. Nordic Walking, фин. Sauvakävely) — ходьба с палками[1], вид физической активности, в которой используются определённая методика занятия и техника ходьбы при помощи специально разработанных палок.",
//				"Появление ходьбы с палками можно отсчитывать с древних времён, когда пастухи и паломники использовали палки как подспорье в условиях сложного рельефа.\r\n"
//						+ "\r\n"
//						+ "В лечебно-оздоровительных учреждениях палки также давно используются в лечебной физкультуре.\r\n"
//						+ "\r\n"
//						+ "Более близкая к современному воплощению версия около 1940 года, связана с профессиональными лыжниками Финляндии, стремившимися поддерживать себя в форме вне лыжного сезона. Они догадались тренироваться без лыж, используя бег с лыжными палками[3].\r\n"
//						+ "\r\n"
//						+ "Первенство описания ходьбы с палками как отдельного вида спорта оспаривается Маури Рэпо (статья «Hiihdon lajiosa» в 1979[4]) и Марко Кантанева (статья «Sauvakävely» в 1997[5]).\r\n"
//						+ "\r\n"
//						+ "Принцип ходьбы с палками основывается на летних упражнениях лыжников и содержит первые описания движений, как их выполнять, анатомические и физиологические причины заниматься этим видом спорта, и какие палки для этого нужны.\r\n"
//						+ "\r\n"
//						+ "В 1988 в США издание Exerstrider[6] представило палки для ходьбы и описало технику ходьбы. У этого вида мало общего с современной ходьбой с палками, палки очень тяжёлые и напоминают треккинговые палки с простым ремнём для руки (позже и без ремня). Пришедшие из разных видов спорта (лыжи и альпинизм), эти виды принципиально отличаются техникой.",
//				"11/01/2023", "images/newsImages/news3.jpg"));
//
//		result.add(new News(4, "Черничный пирог",
//				"По-настоящему летний, яркий, ароматный! Этот пирог украсит собой любой стол по поводу и без!",
//				"1. Для теста: в комбайн просеиваем муку, разрыхлитель, сахарный песок и добавляем охлажденное сливочное масло, перетираем в крошку. 2. В крошку кладем куриное яйцо, сметану, вымешиваем тесто, но не очень долго, иначе масло может растаять. 3. Стол посыпаем мукой, выкладываем тесто, обминаем его и руками раскатываем в пласт. 4. Выкладываем тесто в форму для выпекания, формируем бортики. Отправляем тесто в холодильник на 15-20 минут. 5. Тем временем займемся начинкой: в миску вливаем сметану, добавляем яйца, сахарный песок, ваниль, крахмал (2 столовые ложки). Хорошенько перемешиваем массу венчиком. ",
//				"05/06/2023", "images/newsImages/news4.jpg"));
//		result.add(new News(5, "Сплав на байдарках", "Сплавы выходного дня",
//				"Сплавы выходного дня — это отличный вариант активного отдыха с возможностью завести знакомства с новыми и интересными людьми, отдохнуть и полностью отвлечься и расслабиться после трудовых будней. Байдарочный сплав — одно из самых интересных сочетаний лёгкой физической нагрузки и отдыха на свежем воздухе. Это отличная возможность посетить те места, где вы до этого не бывали, полюбоваться красотой белорусской природы с иного ракурса и подзарядить «батарейки».\r\n"
//						+ "\r\n"
//						+ "Маршруты сплавов могут быть разнообразными, как по сложности, так и по протяженности. Если вы хотите пойти в водный поход компанией, выслушаем ваши пожелания и предложим подходящий вариант маршрута. Если же вы хотите отправиться в поход в одиночку и не имеете желания ждать, пока соберётся ваша компания, мы присоединим вас к уже сформированной группе.",
//				"05/06/2023", "images/newsImages/news5.jpg"));

		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
					"root", "Irina1983");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM news ORDER BY news_date DESC");

			while (rs.next()) {

				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
						rs.getString("content"), rs.getString("news_date"), rs.getString("newscol")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

//		result.add(new News(1, "New ID Buzz will hit the market", "Price gouging ID Buzz buyers would be big mistake",
//				"A generous interior developed according to a fundamental principle that has been continuously perfected over seven decades: maximum use of minimal space. Accompanied by short body overhangs, balanced weight distribution, a low centre of gravity and rear-wheel drive – these are the elements that have set the original apart in this class since 1950. Available in the future as fully electric versions based on the modular electric drive matrix (MEB), in the form of the ID. Buzz and ID. Buzz Cargo..",
//				"06/03/2023", "webapp/images/newsImages/news2.jpg path"));
//
//		result.add(new News(2, "Ландыши весной",
//				"Ла́ндыш ма́йский (лат. Convallária majális) — вид травянистых цветковых растений, распространённый в регионах с умеренным климатом Северного полушария. ",
//				"Травянистое многолетнее растение 15—30 см высотой. Подземное корневище горизонтальное ползучее, не толще гусиного пера, несёт близ верхушки несколько бледных небольших низовых листьев, полускрытых в земле. Корни мелкие, многочисленные, мочковатые.\r\n"
//						+ "\r\n"
//						+ "Надземные побеги укороченные. За низовыми листьями следуют два (редко три) больших, совершенно цельных широколанцетных (или продолговато-эллиптических) заострённых прикорневых листа, между которыми на верхушке корневища находится крупная почка. Из угла низового листа, обхватывающего снизу оба зелёных, выступает цветоносный стебель, несущий кисть из 6—20 цветков, обращённых преимущественно в одну сторону. Цветоносный стебель безлистный либо несёт листья лишь под соцветием; редко — с нитевидными листьями. Ароматные цветки грациозно поникают. Время цветения — с мая по июнь.",
//				"04/06/2023", "images/newsImages/news2.jpg"));
//
//		result.add(new News(3, "Скандинавская ходьба",
//				"Скандина́вская ходьба́ (англ. Nordic Walking, фин. Sauvakävely) — ходьба с палками[1], вид физической активности, в которой используются определённая методика занятия и техника ходьбы при помощи специально разработанных палок.",
//				"Появление ходьбы с палками можно отсчитывать с древних времён, когда пастухи и паломники использовали палки как подспорье в условиях сложного рельефа.\r\n"
//						+ "\r\n"
//						+ "В лечебно-оздоровительных учреждениях палки также давно используются в лечебной физкультуре.\r\n"
//						+ "\r\n"
//						+ "Более близкая к современному воплощению версия около 1940 года, связана с профессиональными лыжниками Финляндии, стремившимися поддерживать себя в форме вне лыжного сезона. Они догадались тренироваться без лыж, используя бег с лыжными палками[3].\r\n"
//						+ "\r\n"
//						+ "Первенство описания ходьбы с палками как отдельного вида спорта оспаривается Маури Рэпо (статья «Hiihdon lajiosa» в 1979[4]) и Марко Кантанева (статья «Sauvakävely» в 1997[5]).\r\n"
//						+ "\r\n"
//						+ "Принцип ходьбы с палками основывается на летних упражнениях лыжников и содержит первые описания движений, как их выполнять, анатомические и физиологические причины заниматься этим видом спорта, и какие палки для этого нужны.\r\n"
//						+ "\r\n"
//						+ "В 1988 в США издание Exerstrider[6] представило палки для ходьбы и описало технику ходьбы. У этого вида мало общего с современной ходьбой с палками, палки очень тяжёлые и напоминают треккинговые палки с простым ремнём для руки (позже и без ремня). Пришедшие из разных видов спорта (лыжи и альпинизм), эти виды принципиально отличаются техникой.",
//				"11/11/22", "images/newsImages/news4.jpg"));
//		result.add(new News(4, "Черничный пирог",
//				"По-настоящему летний, яркий, ароматный! Этот пирог украсит собой любой стол по поводу и без!",
//				"1. Для теста: в комбайн просеиваем муку, разрыхлитель, сахарный песок и добавляем охлажденное сливочное масло, перетираем в крошку. 2. В крошку кладем куриное яйцо, сметану, вымешиваем тесто, но не очень долго, иначе масло может растаять. 3. Стол посыпаем мукой, выкладываем тесто, обминаем его и руками раскатываем в пласт. 4. Выкладываем тесто в форму для выпекания, формируем бортики. Отправляем тесто в холодильник на 15-20 минут. 5. Тем временем займемся начинкой: в миску вливаем сметану, добавляем яйца, сахарный песок, ваниль, крахмал (2 столовые ложки). Хорошенько перемешиваем массу венчиком. ",
//				"05/06/2023", "images/newsImages/news4.jpg"));
//		result.add(new News(5, "Сплав на байдарках", "Сплавы выходного дня",
//				"Сплавы выходного дня — это отличный вариант активного отдыха с возможностью завести знакомства с новыми и интересными людьми, отдохнуть и полностью отвлечься и расслабиться после трудовых будней. Байдарочный сплав — одно из самых интересных сочетаний лёгкой физической нагрузки и отдыха на свежем воздухе. Это отличная возможность посетить те места, где вы до этого не бывали, полюбоваться красотой белорусской природы с иного ракурса и подзарядить «батарейки».\r\n"
//						+ "\r\n"
//						+ "Маршруты сплавов могут быть разнообразными, как по сложности, так и по протяженности. Если вы хотите пойти в водный поход компанией, выслушаем ваши пожелания и предложим подходящий вариант маршрута. Если же вы хотите отправиться в поход в одиночку и не имеете желания ждать, пока соберётся ваша компания, мы присоединим вас к уже сформированной группе.",
//				"05/06/2023", "images/newsImages/news5.jpg"));

		return result;
	}

	@Override
	public News fetchById(int idNews) throws NewsDAOException {

		News news = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_trainingm?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false",
					"root", "Irina1983");
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM news");
			while (rs.next()) {
				if (rs.getInt("id") == idNews) {
					news = new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief_news"),
							rs.getString("content"), rs.getString("news_date"), rs.getString("newscol"));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		

//		switch (id) {
//
//		case 1:
//			news = new News(1, "New ID Buzz will hit the market", "Price gouging ID Buzz buyers would be big mistake",
//					"A generous interior developed according to a fundamental principle that has been continuously perfected over seven decades: maximum use of minimal space. Accompanied by short body overhangs, balanced weight distribution, a low centre of gravity and rear-wheel drive – these are the elements that have set the original apart in this class since 1950. Available in the future as fully electric versions based on the modular electric drive matrix (MEB), in the form of the ID. Buzz and ID. Buzz Cargo.",
//					"06/03/2023", "images/newsImages/news1.jpg");
//			break;
//
//		case 2:
//			news = new News(2, "Ландыши весной",
//					"Ла́ндыш ма́йский (лат. Convallária majális) — вид травянистых цветковых растений, распространённый в регионах с умеренным климатом Северного полушария. ",
//					"Травянистое многолетнее растение 15—30 см высотой. Подземное корневище горизонтальное ползучее, не толще гусиного пера, несёт близ верхушки несколько бледных небольших низовых листьев, полускрытых в земле. Корни мелкие, многочисленные, мочковатые.\r\n"
//							+ "\r\n"
//							+ "Надземные побеги укороченные. За низовыми листьями следуют два (редко три) больших, совершенно цельных широколанцетных (или продолговато-эллиптических) заострённых прикорневых листа, между которыми на верхушке корневища находится крупная почка. Из угла низового листа, обхватывающего снизу оба зелёных, выступает цветоносный стебель, несущий кисть из 6—20 цветков, обращённых преимущественно в одну сторону. Цветоносный стебель безлистный либо несёт листья лишь под соцветием; редко — с нитевидными листьями. Ароматные цветки грациозно поникают. Время цветения — с мая по июнь.",
//					"04/06/2023", "images/newsImages/news2.jpg");
//			break;
//		case 3:
//			news = new News(3, "Скандинавская ходьба",
//					"Скандина́вская ходьба́ (англ. Nordic Walking, фин. Sauvakävely) — ходьба с палками[1], вид физической активности, в которой используются определённая методика занятия и техника ходьбы при помощи специально разработанных палок.",
//					"Появление ходьбы с палками можно отсчитывать с древних времён, когда пастухи и паломники использовали палки как подспорье в условиях сложного рельефа.\r\n"
//							+ "\r\n"
//							+ "В лечебно-оздоровительных учреждениях палки также давно используются в лечебной физкультуре.\r\n"
//							+ "\r\n"
//							+ "Более близкая к современному воплощению версия около 1940 года, связана с профессиональными лыжниками Финляндии, стремившимися поддерживать себя в форме вне лыжного сезона. Они догадались тренироваться без лыж, используя бег с лыжными палками[3].\r\n"
//							+ "\r\n"
//							+ "Первенство описания ходьбы с палками как отдельного вида спорта оспаривается Маури Рэпо (статья «Hiihdon lajiosa» в 1979[4]) и Марко Кантанева (статья «Sauvakävely» в 1997[5]).\r\n"
//							+ "\r\n"
//							+ "Принцип ходьбы с палками основывается на летних упражнениях лыжников и содержит первые описания движений, как их выполнять, анатомические и физиологические причины заниматься этим видом спорта, и какие палки для этого нужны.\r\n"
//							+ "\r\n"
//							+ "В 1988 в США издание Exerstrider[6] представило палки для ходьбы и описало технику ходьбы. У этого вида мало общего с современной ходьбой с палками, палки очень тяжёлые и напоминают треккинговые палки с простым ремнём для руки (позже и без ремня). Пришедшие из разных видов спорта (лыжи и альпинизм), эти виды принципиально отличаются техникой.",
//					"11/11/22", "images/newsImages/news3.jpg");
//
//			break;
//
//		case 4:
//			news = new News(4, "Черничный пирог",
//					"По-настоящему летний, яркий, ароматный! Этот пирог украсит собой любой стол по поводу и без!",
//					"1. Для теста: в комбайн просеиваем муку, разрыхлитель, сахарный песок и добавляем охлажденное сливочное масло, перетираем в крошку. 2. В крошку кладем куриное яйцо, сметану, вымешиваем тесто, но не очень долго, иначе масло может растаять. 3. Стол посыпаем мукой, выкладываем тесто, обминаем его и руками раскатываем в пласт. 4. Выкладываем тесто в форму для выпекания, формируем бортики. Отправляем тесто в холодильник на 15-20 минут. 5. Тем временем займемся начинкой: в миску вливаем сметану, добавляем яйца, сахарный песок, ваниль, крахмал (2 столовые ложки). Хорошенько перемешиваем массу венчиком. ",
//					"05/06/2023", "images/newsImages/news4.jpg");
//
//			break;
//
//		case 5:
//			news = new News(5, "Сплав на байдарках", "Сплавы выходного дня",
//					"Сплавы выходного дня — это отличный вариант активного отдыха с возможностью завести знакомства с новыми и интересными людьми, отдохнуть и полностью отвлечься и расслабиться после трудовых будней. Байдарочный сплав — одно из самых интересных сочетаний лёгкой физической нагрузки и отдыха на свежем воздухе. Это отличная возможность посетить те места, где вы до этого не бывали, полюбоваться красотой белорусской природы с иного ракурса и подзарядить «батарейки».\r\n"
//							+ "\r\n"
//							+ "Маршруты сплавов могут быть разнообразными, как по сложности, так и по протяженности. Если вы хотите пойти в водный поход компанией, выслушаем ваши пожелания и предложим подходящий вариант маршрута. Если же вы хотите отправиться в поход в одиночку и не имеете желания ждать, пока соберётся ваша компания, мы присоединим вас к уже сформированной группе.",
//					"05/06/2023", "images/newsImages/news5.jpg");
//
//			break;
//
//		}

		return news;

	}

	@Override
	public int addNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

}