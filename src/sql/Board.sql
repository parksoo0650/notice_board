CREATE TABLE Board(
	seq_no integer primary key auto_increment not null,
	title varchar(50) not null,
	content varchar(500) not null,
	writer varchar(50) not null,
	regidate varchar(11) not null,
	hit_count integer default 0 null,
	file_name varchar(100) null
)
SELECT MAX(seq_no) AS max FROM Board;

SELECT * FROM Board;

UPDATE Board SET title='aaa', content='ffff' WHERE seq_no=100;

DELETE FROM Board WHERE title = '';

DELETE FROM Board WHERE seq_no = 100;

SELECT * FROM Board WHERE writer='홍길동';

SELECT * FROM Board WHERE title='노우'

SELECT * FROM Board WHERE title='컴백'

SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.*
FROM ( SELECT * FROM Board) 
A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN 1 AND 10;

INSERT INTO Board(title,content,writer,regidate) VALUES('인생','살 무엇을 얼마나','정만호','2017-05-11');
INSERT INTO Board(title,content,writer,regidate) VALUES('봄바람','뼈 쓸쓸한 인생에 곧 있다','홍길동','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('무의미한','그들의 역사를 없는 낙원을 이 있으랴?','함채현','2017-05-05');
INSERT INTO Board(title,content,writer,regidate) VALUES('카톡카톡','거선의 오아이스도 칼이다. ','오나미','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('가족의 큰 딸','어디 얼마나 이것은 약동하다. ','유재석','2017-05-08');
INSERT INTO Board(title,content,writer,regidate) VALUES('상식적','이것은 공자는 자신과 때문이다.','강경준','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('영리하며','찬미를 하는 보라.','나경신','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('작전능력','같은 인간이 청춘의 생생하며, ','김진은','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('사립학교','대중을 소리다.','김지민','2017-04-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('색소폰','청춘의 우리의 그러므로','고우리','2017-03-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('에피소드','인생을 품으며, 끝까지 얼마나 이상을','윤동주','2017-04-09');
INSERT INTO Board(title,content,writer,regidate) VALUES('넬슨 먼츠','뼈 쓸쓸한 인생에 곧 있다.','변용주','2017-01-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('세계평화','이것은 방황하였으며,','김길동','2017-12-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('분리수술','인간의 사랑의 그것을 전인 아름다우냐?','이국주','2017-06-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('놀라운 일','관현악이며, 무엇을 듣기만 피고,','둘리','2017-05-29');
INSERT INTO Board(title,content,writer,regidate) VALUES('천재적','동산에는 인간의 불어 약동하다','도우너','2011-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('항상 천적','현저하게 있는 이상을 인생을 관현악이며, 시들어 ?','뽀로로','2017-08-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('유행어','피가 생명을 같으며','엘사','2017-05-08');
INSERT INTO Board(title,content,writer,regidate) VALUES('사이드쇼','우리 위하여서, 것이다.','에리얼','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('응급조치 강의','눈에 얼마나 곳이 미인을 것이다.','알라딘','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('상담원','꽃 품에 품었기 노년에게서 끓는다.','쟈스민','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('부동산업자','눈에 천자만홍이 이 같지 공자는 할지라도 대한 뿐이다.','오로라','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('제빵사','뜨고, 봄날의 방황하여도, 그들은 위하여','아리','2017-12-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('환경주의자','하는 피에 가슴이 보라.','리븐','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('진보적','이상 없는 속에 두기 것은 운다.','잭스','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('채식주의자','기쁘며, 기관과 풍부하게 천하를 그것은 때문이다.','호머심슨','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('불량학생','바이며, 가진 온갖 것이다.','바트심슨','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('세계대전',' 전인 찾아 우는 이상, 충분히 따뜻한 불어 아니더면, 말이다.','마지심슨','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('비리를','그들의 이상은 맺어, 힘있다.','리사심슨','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('방해하다가','가슴이 돋고, 끓는 힘있다.','모세','2015-08-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('모험담','넣는 못할 그들의 인간에 발휘하기 그리하였는가?','손예진','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('특별한 ','되는 천고에 내는 역사를 그들은 얼마나 이성은 청춘이 봄바람이다.','공지철','2017-03-22');
INSERT INTO Board(title,content,writer,regidate) VALUES('가장','것은 있는 열락의 피가 들어 우리의 싶이 위하여, 말이다.','김제동','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('거짓말같은','청춘이 봄바람이다.','김민지','2016-01-24');
INSERT INTO Board(title,content,writer,regidate) VALUES('이야기를','우리의 싶이 위하여, 말이다.','이영은','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('히틀러를','가슴이 돋고,','서예지','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('봄바람','우리의 싶이 위하여, 말이다.','임지연','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('봄바람','우리의 싶이 위하여, 말이다.','정준영','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('참치 형','우리의 싶이 위하여, 말이다.','강동원','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('비리 폭로','그들의 이상은 맺어, 힘있다.','와트 심슨','2017-05-26');
INSERT INTO Board(title,content,writer,regidate) VALUES('방해','끌어 당긴다','쓰레쉬','2015-08-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('롤','다시 돌아온댜','트위스티드 페이드','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('specaily ','spring windy','matin jukerbug','2017-03-22');
INSERT INTO Board(title,content,writer,regidate) VALUES('new iphone content','iphone is absolute and hologram','steve jobs junior','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('아이언맨','새로운 아이언맨 슈트 등장','tony stark','2016-01-24');
INSERT INTO Board(title,content,writer,regidate) VALUES('우주','우주에도 생명체가 있다.','타노스','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('Spring 프로그래밍 기초분터 중급끼지','spring 초보자를 위한 웹 개발 기초,ajax에 대한 모든 것','lod johnson','2017-05-10');
INSERT INTO Board(title,content,writer,regidate) VALUES('최산 영화','요즘 영화 볼꺼 뭐 있냐?','진 웅','2017-05-25');
INSERT INTO Board(title,content,writer,regidate) VALUES('롤 랭크게임','랭크게임 하실 분 4먕 모집합니다.','롤맨','2017-05-25');
INSERT INTO Board(title,content,writer,regidate) VALUES('흔들린 우정','우리의 우정을 위하여!!!, 말이다.','김보성','2017-05-25');