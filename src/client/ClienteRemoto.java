package client;

import interfaces.Constantes;
import interfaces.Operators;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteRemoto
{
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("****************************");
		System.out.println("*       CALCULADORA        *");
		System.out.println("****************************");
		System.out.println("*     1 - SOMA             *");
		System.out.println("*     2 - SUBTRAÇÃO        *");
		System.out.println("*     3 - MULTIPLICAÇÃO    *");
		System.out.println("*     4 - DIVISÃO          *");
		System.out.println("****************************");
		System.out.println("");
		System.out.println("DIGITE O NÚMERO DA OPERAÇÃO: ");
		int op = reader.nextInt();
		
		if(op == 1 || op == 2 || op == 3 || op == 4)
		{
			System.out.println("DIGITE O PRIMEIRO VALOR: ");
			double valX = reader.nextInt();
			System.out.println("DIGITE O SEGUNDO VALOR: ");
			double valY = reader.nextInt();
			new ClienteRemoto().iniciar(valX, valY, op);
		}
		else
		{
			System.out.println("Operação Inválida");
		}
	}
	public void iniciar(double valorX, double valorY, int operacao)
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost", Constantes.RMI_PORT);
			Operators stub = (Operators) registry.lookup(Constantes.RMI_ID);
			double resultado = 0;
			if(operacao == 1)
			{
				resultado = stub.soma(valorX, valorY);
				System.out.println("A operação " + valorX + " + " + valorY + " obteve o resultado " + resultado);
			}
			else if(operacao == 2)
			{
				resultado = stub.subtracao(valorX, valorY);
				System.out.println("A operação " + valorX + " - " + valorY + " obteve o resultado " + resultado);
			}
			else if(operacao == 3)
			{
				resultado = stub.multiplicacao(valorX, valorY);
				System.out.println("A operação " + valorX + " * " + valorY + " obteve o resultado " + resultado);
			}
			else if(operacao == 4)
			{
				resultado = stub.divisao(valorX, valorY);
				System.out.println("A operação " + valorX + " / " + valorY + " obteve o resultado " + resultado);
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
