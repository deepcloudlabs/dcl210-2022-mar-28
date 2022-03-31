<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:output method="text"/>
   <xsl:template match="/movies">
      create table movies (
           movieId int not null auto_increment,
           imdb char(10) not null unique,
           title char(255) not null,
           year int(4) not null,
           primary key(movieId) 
      ) engine=InnoDB;
      create table genres (       
           genreId int not null auto_increment,    
           name char(16) not null unique,
           primary key(genreId) 
      ) engine=InnoDB;
      create table directors (
           directorId int not null auto_increment,
           imdb char(10) not null unique,
           name char(255) not null,
           primary key(directorId) 
      ) engine=InnoDB;
      create table moviegenres (       
           id int not null auto_increment,    
           movieId int not null,
           genreId int not null,
           primary key(id) 
      ) engine=InnoDB;
      create table moviedirectors (
           id int not null auto_increment,
           movieId int not null,
           directorId int not null,
           primary key(id) 
      ) engine=InnoDB;
      insert into genres(name) values ('Action'),('Adventure'),('Animation'),('Anime'),('Biography'),('Cartoons'),
                                      ('Comedy'),('Crime'),('Disaster'),('Documentary'),('Drama'),('Family'),('Fantasy'),
                                      ('Film-noir'),('Horror'),('Musical'),('Mystery'),('Romance'),('Sci-fi'),('Thriller'),
                                      ('War'),('Western');
       <xsl:for-each select="movie">
          insert into movies(imdb,title,`year`) values ('<xsl:value-of select="@imdb"/>','<xsl:value-of select="title"/>',<xsl:value-of select="year"/>);
          select movieId into @mid from movies where imdb = '<xsl:value-of select="@imdb"/>';
          <xsl:for-each select="directors/director"> 
             insert into directors values (NULL,'<xsl:value-of select="@imdb"/>','<xsl:value-of select="."/>');
             select directorId into @did from directors where imdb='<xsl:value-of select="@imdb"/>';
             insert into moviedirectors(movieId,directorId) values (@mid,@did);  
          </xsl:for-each> 
          <xsl:for-each select="genres/genre">
             select genreId into @gid from genres where name = '<xsl:value-of select="."/>';
             insert into moviegenres(movieId,genreId) values (@mid,@gid);
          </xsl:for-each> 
       </xsl:for-each>
   </xsl:template>
</xsl:stylesheet>