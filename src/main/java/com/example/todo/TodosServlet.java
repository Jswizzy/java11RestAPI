package com.example.todo;

import com.example.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class TodosServlet extends HttpServlet {

	private static final Gson GSON = new GsonBuilder().create();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String json = GSON.toJson(Todos.todos.values());

		resp.setStatus(200);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(json);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = Util.readInputStream(req.getInputStream());
		Todo todo = GSON.fromJson(json, Todo.class);

		todo.setId(Todos.nextId());
		Todos.todos.put(todo.getId(), todo);

		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println(GSON.toJson(todo));
	}
}