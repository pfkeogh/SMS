README - Patrick F.
Setup guide
1.Import as existing Maven project
2.Update Maven dependencies
3.Change Hibernate configuration settings in Webconfig SessionFactory and DataSource to match you DB
4.Set Hibernate ddl to create on first run, afterwards use update
5.Publish to server (Tested on Tomcat 9.0, Oracle12c)