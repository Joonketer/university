학사관리 데이터베이스

**요구사항 명세서 ![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.003.jpeg)**

주요업무로는 학사, 수강관리가 있으며 각 업무 별로 요구사항 명세서를 작성하였다. 

회원 – 로그인, 교수 및 학생의 회원정보, 정보 수정 

수강 – 로그인, 수강, 학사관리 성적 – 학사 관리 

**Entity**

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.004.jpeg)

**테이블명세서**

student : 학생관리, 소속학과 및 지도교수 등 (Entity)

professor : 교수관리, 소속학과 및 전공 등 (Entity)

department :  학과관리, 학과 및 전공 등 (Entity) subject : 교과목, 개설학과 및 학점 등 (Entity) admin : id,  password관리, 학생 또는 교수

(relation)

grade : 성적, 점수 및 학점 등(relation)

attend : 수강, 담당교수 및 교과목 등(relation)

ⓒSaebyeol Yu. Saebyeol’s PowerPoint
**Database Schema**

OS: Windos 10!

DBMS: MySQL 8.0 Community Interface: MySQL Workbench 8.0.30

Schema

schema: UniversityDB

entitiy: student, professor, department, subject relation: attend, grade, admin

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.005.png)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.006.png)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.007.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.008.png)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.009.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.010.png)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.011.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.012.png)

**Database Schema**

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.013.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.014.png) 


**Database Schema**

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.015.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.016.png)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.017.jpeg)

![](img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.018.png)


[ref1]: img\Aspose.Words.7b83ad5b-126c-4d1c-9f90-a2bbb6fb01cd.002.png
