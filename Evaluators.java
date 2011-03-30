
public interface Evaluators {
	static int pawnB[][] = {{0,  0,  0,  0,  0,  0,  0,  0},
							{50, 50, 50, 50, 50, 50, 50, 50},
							{10, 10, 20, 30, 30, 20, 10, 10},
							{5,  5, 10, 25, 25, 10,  5,  5},
							{0,  0,  0, 20, 20,  0,  0,  0},
							{5, -5,-10,  0,  0,-10, -5,  5},
							{5, 10, 10,-20,-20, 10, 10,  5},
							{0,  0,  0,  0,  0,  0,  0,  0} };
	
	static int pawnW[][] = {{0,  0,  0,  0,  0,  0,  0,  0},
							{5, 10, 10,-20,-20, 10, 10,  5},
							{5, -5,-10,  0,  0,-10, -5,  5},
							{0,  0,  0, 20, 20,  0,  0,  0},
							{5,  5, 10, 25, 25, 10,  5,  5},
							{10, 10, 20, 30, 30, 20, 10, 10},
							{50, 50, 50, 50, 50, 50, 50, 50},
							{0,  0,  0,  0,  0,  0,  0,  0}};
	
	static int knightB[][] = {{-50,-40,-30,-30,-30,-30,-40,-50},
							 {-40,-20,  0,  0,  0,  0,-20,-40},
							 {-30,  0, 10, 15, 15, 10,  0,-30},
							 {-30,  5, 15, 20, 20, 15,  5,-30},
							 {-30,  0, 15, 20, 20, 15,  0,-30},
							 {-30,  5, 10, 15, 15, 10,  5,-30},
							 {-40,-20,  0,  5,  5,  0,-20,-40},
							 {-50,-40,-30,-30,-30,-30,-40,-50}};
	
	static int knightW[][] = {{-50,-40,-30,-30,-30,-30,-40,-50},
							  {-40,-20,  0,  5,  5,  0,-20,-40},
							  {-30,  5, 10, 15, 15, 10,  5,-30},
							  {-30,  0, 15, 20, 20, 15,  0,-30},
							  {-30,  5, 15, 20, 20, 15,  5,-30},
							  {-30,  0, 10, 15, 15, 10,  0,-30},
							  {-40,-20,  0,  0,  0,  0,-20,-40},
							  {-50,-40,-30,-30,-30,-30,-40,-50}};
	
	
	
	static int bishopB[][] = {{-20,-10,-10,-10,-10,-10,-10,-20},
							 {-10,  0,  0,  0,  0,  0,  0,-10},
							 {-10,  0,  5, 10, 10,  5,  0,-10},
							 {-10,  5,  5, 10, 10,  5,  5,-10},
							 {-10,  0, 10, 10, 10, 10,  0,-10},
							 {-10, 10, 10, 10, 10, 10, 10,-10},
							 {-10,  5,  0,  0,  0,  0,  5,-10},
							 {-20,-10,-10,-10,-10,-10,-10,-20}};
	
	static int bishopW[][] = {{-20,-10,-10,-10,-10,-10,-10,-20},
							  {-10,  5,  0,  0,  0,  0,  5,-10},
							  {-10, 10, 10, 10, 10, 10, 10,-10},
							  {-10,  0, 10, 10, 10, 10,  0,-10},
							  {-10,  5,  5, 10, 10,  5,  5,-10},
							  {-10,  0,  5, 10, 10,  5,  0,-10},
							  {-10,  0,  0,  0,  0,  0,  0,-10},
							  {-20,-10,-10,-10,-10,-10,-10,-20}};
	
	static int rookW[][] = {{0,  0,  0,  5,  5,  0,  0,  0},
							{-5,  0,  0,  0,  0,  0,  0, -5},
							{-5,  0,  0,  0,  0,  0,  0, -5},
							{-5,  0,  0,  0,  0,  0,  0, -5},
							{-5,  0,  0,  0,  0,  0,  0, -5},
							{-5,  0,  0,  0,  0,  0,  0, -5},
							{5, 10, 10, 10, 10, 10, 10,  5},
							{0,  0,  0,  0,  0,  0,  0,  0}};
	
	static int rookB[][] = {{0,  0,  0,  0,  0,  0,  0,  0},
		  					{5, 10, 10, 10, 10, 10, 10,  5},
		  					{-5,  0,  0,  0,  0,  0,  0, -5},
		  					{-5,  0,  0,  0,  0,  0,  0, -5},
		  					{-5,  0,  0,  0,  0,  0,  0, -5},
		  					{-5,  0,  0,  0,  0,  0,  0, -5},
		  					{-5,  0,  0,  0,  0,  0,  0, -5},
		  					{0,  0,  0,  5,  5,  0,  0,  0}};
	
	static int queen[][] = {{-20,-10,-10, -5, -5,-10,-10,-20},
        					{-10,  0,  0,  0,  0,  0,  0,-10},
        					{-10,  0,  5,  5,  5,  5,  0,-10},
        					{-5,  0,  5,  5,  5,  5,  0, -5},
        					{-5,  0,  5,  5,  5,  5,  0, -5},
        					{-10,  0,  5,  5,  5,  5,  0,-10},
        					{-10,  0,  0,  0,  0,  0,  0,-10},
        					{-20,-10,-10, -5, -5,-10,-10,-20}};
	
	static int kingB[][] = {{-30,-40,-40,-50,-50,-40,-40,-30},
        					{-30,-40,-40,-50,-50,-40,-40,-30},
        					{-30,-40,-40,-50,-50,-40,-40,-30},
        					{-30,-40,-40,-50,-50,-40,-40,-30},
        					{-20,-30,-30,-40,-40,-30,-30,-20},
        					{-10,-20,-20,-20,-20,-20,-20,-10},
        					{20, 20,  0,  0,  0,  0, 20, 20},
        					{20, 30, 10,  0,  0, 10, 30, 20}};

	static int kingW[][] = {{20, 30, 10,  0,  0, 10, 30, 20},
         					{20, 20,  0,  0,  0,  0, 20, 20},
         					{-10,-20,-20,-20,-20,-20,-20,-10},
         					{-20,-30,-30,-40,-40,-30,-30,-20},
         					{-30,-40,-40,-50,-50,-40,-40,-30},
         					{-30,-40,-40,-50,-50,-40,-40,-30},
         					{-30,-40,-40,-50,-50,-40,-40,-30},
         					{-30,-40,-40,-50,-50,-40,-40,-30}};
}
