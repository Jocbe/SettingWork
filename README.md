Our prototype can hold three types of entities:

1. Questions
Question is a basic entity, that holds contents of a single question. At the
prototype stage we decided to keep it in plain text form, but in the final
project we will extend its functionality to store questions in another forms
(e.g. PDFs, LaTeX code, BBcode enriched plaintext etc.).

2. Sets
Set is a collection of questions. At this stage we kept it as simple as
possible - sets are constructed by a simple ManyToOne relation between multiple
questions and one set.

3. User
User is a representation of a person using a system. User can create sets.
Questions cannot be directly created by a user (this is explained later).
Relation between users and sets is again a simple ManyToOne relation.

Those 3 simple entities enabled us to build a system, that supports all the
features we planned to implement:

1. Creating Users

2. Creating Sets
Each user can create their own sets. There's no limit to how many sets can a
user create. Sets are not transferable between users. They can be renamed, but
they cannot be deleted.

3. Creating new questions in Sets
Questions cannot be created on their own. They have to be added to the database
as a part of an existing set. Owner of the set the question is created in is
assumed to be its author (i.e. it is not possible to add your own questions to
other users' sets).

4. Forking question
You can copy existing question from one set to another, even between users.
Forking questions preserves their authors, so even if you fork other user's
question to your own set, original author stays the same. Beyond that there is
no other link between the original question and its copy.

5. Editing questions
It is possible to modify the content of a question already existing in the
database. This does not affect in any way forks of that question. This doesn't
change author of the question, so even if you edit another user's question, 
original author stays the same.

System we created this week is very simple, but implements all the features we
wanted. It revealed some weaknesses in our prototype design, though.

1. Forking a question creates vast amount of copies of one question. All those
copies belongs to one user, so we can end up with original author having
hundreds of questions in their collection, even though they actually created
just one of them.
There is no link whatsoever between original question and its forks. There is no
way to track history of changes of a given question.