public class Solver {
    private static final int DEPTH = 4;

    public static Board.Location findMove(Board board, boolean firstPlayerToMove, boolean pruning) {
        NodeResult result = minimax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, firstPlayerToMove, pruning);
//        System.out.println(pruning ? "AB Pruning Algorithm:" : "Minimax Algorithm:");
//        System.out.println("Board Size - (" + Board.SIZE + "x" + Board.SIZE + ")");
//        System.out.println("Nodes Expanded - " + result.nodesExpanded);
//        System.out.println("Depth - " + DEPTH);
        return result.move;
    }

    private static NodeResult minimax(Board board, int depth, int alpha, int beta,
                                boolean firstPlayerMove, boolean pruning) {
        if (board.isOver() || depth == DEPTH) {
            return new NodeResult(board, null, board.evaluate(firstPlayerMove), 1, 0);
        }

        if (firstPlayerMove) {
            int maxDepth = 0;
            int nodesExpanded = 1;
            NodeResult best = null;
            for (Board.Location loc : board.getPossibleMoves()) {
                NodeResult res = minimax(board.makeMove(loc, firstPlayerMove), depth+1, alpha, beta, false, pruning);
                nodesExpanded += res.nodesExpanded;
                maxDepth = Math.max(maxDepth, 1 + res.depthLevel);
                if (best == null || res.value > best.value) {
                    best = new NodeResult(board, loc, res.value);
                }
                // updating search statistics
                best.nodesExpanded = nodesExpanded;
                best.depthLevel = maxDepth;
                // making pruning if necessary
                if (pruning) {
                    if (best.value >= beta) {
                        break;
                    }
                    alpha = Math.max(alpha, best.value);
                }
            }
            return best;
        }
        else {
            int maxDepth = 0;
            int nodesExpanded = 1;
            NodeResult best = null;
            for (Board.Location loc : board.getPossibleMoves()) {
                NodeResult res = minimax(board.makeMove(loc, firstPlayerMove), depth+1, alpha, beta, true, pruning);
                nodesExpanded += res.nodesExpanded;
                maxDepth = Math.max(maxDepth, 1 + res.depthLevel);
                if (best == null || res.value < best.value) {
                    best = new NodeResult(board, loc, res.value);
                }
                // updating search statistics
                best.nodesExpanded = nodesExpanded;
                best.depthLevel = maxDepth;
                // making pruning if necessary
                if (pruning) {
                    if (best.value <= alpha) {
                        break;
                    }
                    beta = Math.min(beta, best.value);
                }
            }
            return best;
        }
    }

    private static class NodeResult {
        Board board;
        Board.Location move;
        int value;
        int nodesExpanded;
        int depthLevel;

        public NodeResult(Board board, Board.Location move, int value, int nodesExpanded, int depthLevel) {
            this.board = board;
            this.move = move;
            this.value = value;
            this.nodesExpanded = nodesExpanded;
            this.depthLevel = depthLevel;
        }

        public NodeResult(Board board, Board.Location move, int value) {
            this(board, move, value, 0, 0);
        }
    }
}
