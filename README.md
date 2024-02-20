README for Java Data Analysis Toolkit
This toolkit comprises several Java classes designed to analyze data, focusing on ratings, genders, keywords, professors, and schools. Each class extends the abstract DataAnalyzer class, utilizing a MyHashTable for efficient data storage and retrieval.

DataAnalyzer.java: Abstract base class defining the framework for data analysis, including methods for information extraction and keyword-based distribution analysis.

GenderByKeyword.java: Analyzes gender distribution based on keywords, utilizing MyHashTable to store gender counts for each keyword.

MyHashTable.java: Generic hash table implementation supporting basic operations like put, get, and remove, optimized for key-value pair storage and retrieval.

RatingByGender.java: Focuses on analyzing ratings by gender, categorizing data into distributions of ratings and difficulty levels.

RatingByKeyword.java: Analyzes ratings based on keywords found in comments, storing distributions of ratings for each keyword.

RatingDistributionByProf.java: Maps professors to their rating distributions, allowing analysis of ratings per professor.

RatingDistributionBySchool.java: Analyzes rating distributions by school, calculating average ratings for professors within schools and organizing this data by school names.

This toolkit serves as a comprehensive solution for data analysis needs, particularly in educational settings, enabling detailed insights into ratings, gender distributions, and keyword relevancies.
