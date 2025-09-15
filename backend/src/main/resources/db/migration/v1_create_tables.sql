CREATE TABLE IF NOT EXISTS "users" (
	"id" serial NOT NULL UNIQUE,
	"username" varchar(50) NOT NULL UNIQUE,
	"password" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL UNIQUE,
	"role" varchar(20) NOT NULL DEFAULT 'USER',
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "projects" (
	"id" serial NOT NULL UNIQUE,
	"name" varchar(255) NOT NULL,
	"description" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "project_users" (
	"project_id" bigint NOT NULL,
	"user_id" bigint NOT NULL,
	"role" varchar(255) NOT NULL DEFAULT 'MEMBER',
	PRIMARY KEY ("project_id", "user_id")
);

CREATE TABLE IF NOT EXISTS "tasks" (
	"id" serial NOT NULL UNIQUE,
	"name" varchar(255) NOT NULL,
	"description " varchar(255) NOT NULL,
	"project_id" bigint NOT NULL,
	"assignee_id" bigint,
	"status " varchar(20) NOT NULL DEFAULT 'TO_DO',
	"priority " varchar(20) NOT NULL DEFAULT 'MEDIUM',
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "board_columns" (
	"id" serial NOT NULL UNIQUE,
	"project_id" bigint NOT NULL,
	"name" varchar(50) NOT NULL,
	"position " bigint NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "comments" (
	"id" serial NOT NULL UNIQUE,
	"task_id" bigint NOT NULL,
	"user_id" bigint NOT NULL,
	"content" varchar(255) NOT NULL,
	"created_at" timestamp with time zone NOT NULL DEFAULT now(),
	PRIMARY KEY ("id")
);



ALTER TABLE "project_users" ADD CONSTRAINT "project_users_fk0" FOREIGN KEY ("project_id") REFERENCES "projects"("id");

ALTER TABLE "project_users" ADD CONSTRAINT "project_users_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "tasks" ADD CONSTRAINT "tasks_fk3" FOREIGN KEY ("project_id") REFERENCES "projects"("id");

ALTER TABLE "tasks" ADD CONSTRAINT "tasks_fk4" FOREIGN KEY ("assignee_id") REFERENCES "users"("id");
ALTER TABLE "board_columns" ADD CONSTRAINT "board_columns_fk1" FOREIGN KEY ("project_id") REFERENCES "projects"("id");
ALTER TABLE "comments" ADD CONSTRAINT "comments_fk1" FOREIGN KEY ("task_id") REFERENCES "tasks"("id");

ALTER TABLE "comments" ADD CONSTRAINT "comments_fk2" FOREIGN KEY ("user_id") REFERENCES "users"("id");