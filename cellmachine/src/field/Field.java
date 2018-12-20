package field;

import java.util.ArrayList;

import animal.Fox;
import animal.Rabbit;
import cell.Cell;

public class Field {
	private int width;
	private int height;
	private Cell[][] field;
	private boolean[][] vis;

	private final int MAX_Q;
	private Location[] queue;
	private int head, tail;

	private static final int[] dr = { -1, 1, 0, 0 };
	private static final int[] dc = { 0, 0, -1, 1 };

	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		field = new Cell[height][width];
		vis = new boolean[height][width];

		MAX_Q = height * width;
		queue = new Location[MAX_Q];
		for ( int i = 0; i < MAX_Q; i++ ) {
			queue[i] = new Location();
		}
	}

	public void init(){
		for(int r = 0; r< height; r++){
			for (int c = 0; c <width; c++){
				double possiblity = Math.random();
				if(possiblity < 0.015)
				{
					this.place(r,c,new Fox());
				}
				else if ( possiblity < 0.15)
				{
					this.place(r,c,new Rabbit());
				}
			}
		}
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public void place(int row, int col, Cell o){
		field[row][col] = o;
	}

//	public Cell place(int row, int col, Cell o) {
//		Cell ret = field[row][col];
//		field[row][col] = o;
//		return ret;
//	}

	public void remove(int row,int col){
		field[row][col] = null;
	}
	
	public Cell get(int row, int col) {
		return field[row][col];
	}
	
//	public Cell[] getNeighbour(int row, int col) {
//		ArrayList<Cell> list = new ArrayList<Cell>();
//		for ( int i=-1; i<2; i++ ) {
//			for ( int j=-1; j<2; j++ ) {
//				int r = row+i;
//				int c = col+j;
//				if ( r >-1 && r<height && c>-1 && c<width && !(r== row && c == col) ) {
//					list.add(field[r][c]);
//				}
//			}
//		}
//		return list.toArray(new Cell[list.size()]);
//	}

	public Cell[] getNeighbour(int row,int col){
		ArrayList<Cell> neighbour = new ArrayList<Cell>();
		for ( int r = row - 1; r <= row + 1; r++ ) {
			for ( int c = col - 1; c <= col + 1; c++ ) {
				if ( r >= 0 && r < height && c >= 0 && c < width && !(r == row && c == col) ) {
					Cell cell = field[r][c];
					if ( null != cell ) {
						neighbour.add(cell);
					}
				}
			}
		}
		return neighbour.toArray(new Cell[neighbour.size()]);
	}

	public Location[] getFreeNeighbourLoc(int row, int col) {
		ArrayList<Location> locs = new ArrayList<Location>();
		for ( int r = row - 1; r <= row + 1; r++ ) {
			for ( int c = col - 1; c <= col + 1; c++ ) {
				if ( r >= 0 && r < height && c >= 0 && c < width && !(r == row && c == col) ) {
					Cell cell = field[r][c];
					if ( null == cell ) {
						locs.add(new Location(r, c));
					}
				}
			}
		}
		return locs.toArray(new Location[locs.size()]);
	}

	public Location[] getRabbitNeighbourLoc(int row, int col) {
		ArrayList<Location> locs = new ArrayList<Location>();
		for ( int r = row - 1; r <= row + 1; r++ ) {
			for ( int c = col - 1; c <= col + 1; c++ ) {
				if ( r >= 0 && r < height && c >= 0 && c < width && !(r == row && c == col) ) {
					Cell cell = field[r][c];
					if ( cell instanceof Rabbit ) {
						locs.add(new Location(r, c));
					}
				}
			}
		}
		return locs.toArray(new Location[locs.size()]);
	}

	public int BFSFox(int row, int col) {
		int dir = 0;
		boolean found = false;
		this.initBFS();

		vis[row][col] = true;
		queue[tail].row = row;
		queue[tail].col = col;
		tail++;

		int r = 0, c = 0, rr, cc;
		while ( head != tail ) {
			r = queue[head].row;
			c = queue[head].col;
			head++;
			if ( field[r][c] instanceof Fox ) {
				found = true;
				break;
			}
			for ( int i = 0; i < 4; i++ ) {
				rr = r + dr[i];
				cc = c + dc[i];
				if ( rr >= 0 && rr < height && cc >= 0 && cc < width && !vis[rr][cc] ) {
					vis[rr][cc] = true;
					queue[tail].row = rr;
					queue[tail].col = cc;
					tail++;
				}
			}
		}
		if ( found ) {
			int up = row - r;
			int down = -up;
			int left = col - c;
			int right = -left;

			if ( up > 0 ) {
				if ( left > 0 ) {
					if ( up < left ) {
						dir = 4;
					} else {
						dir = 2;
					}
				} else {
					if ( up < right ) {
						dir = 3;
					} else {
						dir = 2;
					}
				}
			} else {
				if ( left > 0 ) {
					if ( down < left ) {
						dir = 4;
					} else {
						dir = 1;
					}
				} else {
					if ( down < right ) {
						dir = 3;
					} else {
						dir = 1;
					}
				}
			}
		}
		return dir;
	}

	public int BFSRabbit(int row, int col) {
		int dir = 0;
		boolean found = false;
		this.initBFS();

		vis[row][col] = true;
		queue[tail].row = row;
		queue[tail].col = col;
		tail++;

		int r = 0, c = 0, rr, cc;
		while ( head != tail ) {
			r = queue[head].row;
			c = queue[head].col;
			head++;
			if ( field[r][c] instanceof Rabbit ) {
				found = true;
				break;
			}
			for ( int i = 0; i < 4; i++ ) {
				rr = r + dr[i];
				cc = c + dc[i];
				if ( rr >= 0 && rr < height && cc >= 0 && cc < width && !vis[rr][cc] ) {
					vis[rr][cc] = true;
					queue[tail].row = rr;
					queue[tail].col = cc;
					tail++;
				}
			}
		}
		if ( found ) {
			int up = row - r;
			int down = -up;
			int left = col - c;
			int right = -left;

			if ( up > 0 ) {
				if ( left > 0 ) {
					if ( up < left ) {
						dir = 3;
					} else {
						dir = 1;
					}
				} else {
					if ( up < right ) {
						dir = 4;
					} else {
						dir = 1;
					}
				}
			} else {
				if ( left > 0 ) {
					if ( down < left ) {
						dir = 3;
					} else {
						dir = 2;
					}
				} else {
					if ( down < right ) {
						dir = 4;
					} else {
						dir = 2;
					}
				}
			}
		}
		return dir;
	}

	public void animalMove(int row, int col, Location next_loc) {
		field[next_loc.row][next_loc.col] = field[row][col];
		field[row][col] = null;
	}

	public void animalMove(int row, int col, int dir) {
		switch ( dir ) {
			case 1:
				if ( row - 1 >= 0 && null == field[row - 1][col] ) {
					field[row - 1][col] = field[row][col];
					field[row][col] = null;
				}
				break;
			case 2:
				if ( row + 1 < height && null == field[row + 1][col] ) {
					field[row + 1][col] = field[row][col];
					field[row][col] = null;
				}
				break;
			case 3:
				if ( col - 1 >= 0 && null == field[row][col - 1] ) {
					field[row][col - 1] = field[row][col];
					field[row][col] = null;
				}
				break;
			case 4:
				if ( col + 1 < width && null == field[row][col + 1] ) {
					field[row][col + 1] = field[row][col];
					field[row][col] = null;
				}
				break;
			default:
				break;
		}
	}

	private void initBFS() {
		head = tail = 0;
		for ( int r = 0; r < height; r++ ) {
			for ( int c = 0; c < width; c++ ) {
				vis[r][c] = false;
			}
		}
	}

	public void clear() {
		for ( int i=0; i<height; i++ ) {
			for ( int j=0; j<width; j++ ) {
				field[i][j] = null;
			}
		}
	}
}
