 git add .
 gir commit -m "statement"
 
 git add .
 git commit --amend --no-edit
 git push


git pull origin master --allow-unrelated-histories
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate