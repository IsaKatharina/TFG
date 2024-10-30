var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();

var app = builder.Build();

// Configure the HTTP request pipeline.

app.UseAuthorization();

app.MapControllers();

app.MapControllerRoute(
    name: "deafault", 
    pattern: "{controller=Usuarios}");

app.Run();